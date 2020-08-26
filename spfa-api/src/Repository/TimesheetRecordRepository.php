<?php

namespace App\Repository;

use ApiPlatform\Core\Bridge\Elasticsearch\Metadata\Resource\Factory\ElasticsearchOperationResourceMetadataFactory;
use App\Entity\TimesheetRecord;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Common\Persistence\ManagerRegistry;
use Doctrine\ORM\Query\ResultSetMapping;

/**
 * @method TimesheetRecord|null find($id, $lockMode = null, $lockVersion = null)
 * @method TimesheetRecord|null findOneBy(array $criteria, array $orderBy = null)
 * @method TimesheetRecord[]    findAll()
 * @method TimesheetRecord[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class TimesheetRecordRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, TimesheetRecord::class);
    }

    /***
     * @param $entity
     * @return mixed
     * @throws \Doctrine\ORM\ORMException
     * @throws \Doctrine\ORM\OptimisticLockException
     * save entity to database
     */
    public function save($entity): TimesheetRecord
    {
        //tell the entity manager to track changes to the object
        $this->_em->persist($entity);
        //push the changes of the entity objects to the database
        $this->_em->flush();
        return $entity;
    }

    /***
     * @param $entity
     * @throws \Doctrine\ORM\ORMException
     * @throws \Doctrine\ORM\OptimisticLockException
     * this function delete entity from database
     */
    public function delete($entity)
    {
        $this->_em->remove($entity);
        $this->_em->flush();
    }

    /**
     * get the hours spent on each day and each task category.
     * this function will generate report based on start date, end date, and personID
     * @param string $start
     * @param string $end
     * @param int $personID
     * @return mixed[] return array with 3 fields: 'd' as the timesheet date, 'hours' as the hours spent on that date and on each task category
     *                                             'cat' as the task category
     * @throws \Doctrine\DBAL\DBALException
     */
    public function selectDetailReport1(string $start, string $end, int $personID){
        //connect the database
        $conn = $this->getEntityManager()
            ->getConnection();
        //prepare the query statement. select the start date as timesheet date, end time minus start time as hours, category name as cat
        //                             from ('timesheetRecord' left join 'task', then left join 'category')

        $sql=<<<EOF
SELECT date(tr.start_time) as d, round(((extract(epoch from tr.end_time) - extract(epoch from tr.start_time))/(60.0*60.0))::numeric,1) as hours, category_name as cat from TimesheetRecord tr LEFT JOIN task tk on tr.task_ID = tk.task_id 
		JOIN category c on tk.category_id = c.category_id
		WHERE date(tr.start_time)>=date(?) and date(tr.end_time)<=date(?) and person_id=?
EOF;
        $stmt=$conn->prepare($sql);
        $stmt->bindValue(1,$start);
        $stmt->bindValue(2,$end);
        $stmt->bindValue(3,$personID);
        $stmt->execute();
        $result=$stmt->fetchAll();
        $conn->close();
        return $result;
    }

    /**
     * This query will gather the data needed to put together SPFA Detail Report #2
     *
     * @param string $start - Start date of data to gather
     * @param string $end - End date of data to gather
     * @param int $personID - The ID of the person whose data to gather
     * @return mixed[] - Will return either a collection of data, or none
     * @throws \Exception
     */
    public function selectDetailReport2(string $start, string $end, int $personID)
    {
        //Use query builder to gather the data
        $qb = $this->createQueryBuilder('tr');
        //Add 1 day (to include the whole day to report on)
        $end=(new \DateTime($end))->add(new \DateInterval('P1D'))->format("Y-m-d");
        //Build the query statement from all of the records and tables
        $qb->select('tr.recordID')
            ->addSelect('tr.start')
            ->addSelect('tr.end')
            ->addSelect('t.title')
            ->addSelect('c.categoryName')
            ->innerJoin('App\Entity\Task','t','WITH','tr.task = t.taskID')
            ->innerJoin('App\Entity\Category','c','WITH','t.category = c.categoryID')
            ->where('tr.person = ?1')
            ->andWhere('tr.start >= ?2')
            ->andWhere('tr.end <= ?3')
            ->setParameter(1,$personID)
            ->setParameter(2,$start)
            ->setParameter(3,$end);

        //Generate the query from the querybuilder
        $query = $qb->getQuery();
        //Execute the query and return the results
        return $query->getResult();
    }
}
