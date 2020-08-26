<?php

namespace App\Repository;

use App\Entity\Category;
use App\Entity\CategoryType;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Common\Persistence\ManagerRegistry;

/**
 * @method Category|null find($id, $lockMode = null, $lockVersion = null)
 * @method Category|null findOneBy(array $criteria, array $orderBy = null)
 * @method Category[]    findAll()
 * @method Category[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class CategoryRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Category::class);
    }

    /**
     * Finds and returns an array of Categories using the array of key pair values as a filter.
     * @param $filterArray -> A Associative Array representation of a Category Object. This array will act as our 'filter'
     *                          when building our where clause in the createQueryBuilder.
     * @return array -> Return an array of Categories. Can be an empty array of no results are found.
     */
    public function findByField(array $filterArray) : array {

        //Initialize the createQueryBuilder
        $qb = $this->createQueryBuilder('c');

        $counter = 0;
        $setParamsArray = [];
        //We loop through each key pair value
        foreach ($filterArray as $field=>$value) {

            //We have to check for specifically for parentCategory
            //As it requires a specific where clause.
            if($field == 'parentCategory'){
                $qb->andwhere("c.parentCategory IN (?$counter)" );
            } else {
                //simple where clause using the LIKE keyword so we can use partial words.
                $qb->andWhere("c.$field LIKE ?$counter");
                $value = '%' . $value . '%';
            }

            $setParamsArray["$counter"] = "$value";

            $counter++;
        }

        $qb->setParameters($setParamsArray);

        //We execute the query and return the results as an array.
        return $qb->getQuery()->getResult();
    }

    /**
     * Deletes the category that was passed in.
     *
     * @param Category $category -> The category being deleted.
     * @return string -> if anything is returned from $delete(), it would be an exception error message.
     *
     * @throws \Doctrine\ORM\ORMException
     * @throws \Doctrine\ORM\OptimisticLockException
     */
    public function delete(Category $category)
    {

        try {
            //Tells the entity manager to remove a category
            $this->_em->remove($category);

            //push the changes of the entity objects to the database
            $this->_em->flush();
        } catch (ORMException $exception) {
            return $exception->getMessage();
        }

    }

    /**
     * Get all the descendant categories of the root category
     * @param string $categoryName the root category's name
     * @return mixed[] descendant categories array
     * @throws \Doctrine\DBAL\DBALException
     */
    public function getGroupCategories(string $categoryName){
        //connect to database
        $conn = $this->getEntityManager()
            ->getConnection();
        //prepare the recursive query statement to select the children of one category.
        // Firstly create 'children_of' table by left self join 'category' table, select the parent category's Name and it's child
        //then create recursive query table as 'group_category', the first initial query is the value of $categoryName,
        // then recursively union all the children of this root category from ('children_of' join new created table row in 'group_category' on name)
        // At last select category name from group_category join category on category name.
        $sql=<<<EOF
         with recursive children_of(name,child) as (
            select i.category_name as name,o.category_name as child from category i left join category o on i.category_id=o.parent_id),
		group_category(name) AS(VALUES(?)
            UNION  ALL

            select child from children_of c JOIN group_category g USING(name))
			SELECT category.category_name as catName from group_category, category
				where group_category.name=category.category_name
EOF;

        //execute the query
        $stmt = $conn->prepare($sql);
        $stmt->bindValue(1,$categoryName);
        //fetch the query table rows
        $stmt->execute();
        $result=$stmt->fetchAll();
        //close the connection
        $conn->close();
        return $result;
    }

    public function getTopLevelTaskCategories()
    {

        $qb = $this->createQueryBuilder('c');
        $qb->select('c.categoryName')
            ->where('c.categoryType = ?1')
            ->andWhere('c.parentCategory IS NULL')
            ->setParameter(1,CategoryType::TASK);

        $query = $qb->getQuery();
        return $query->getResult();
    }
}
