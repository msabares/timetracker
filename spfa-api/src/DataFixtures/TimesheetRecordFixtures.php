<?php

namespace App\DataFixtures;


use App\Entity\Category;
use App\Repository\CategoryRepository;
use App\Repository\TimesheetRecordRepository;
use App\Serializer\EntityNormalizer;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Bundle\FixturesBundle\FixtureGroupInterface;
use Doctrine\Common\DataFixtures\OrderedFixtureInterface;
use Doctrine\Common\Persistence\ObjectManager;
use App\Entity\TimesheetRecord;
use App\Entity\Person;
use App\Entity\Task;
use Doctrine\Common\DataFixtures\FixtureInterface;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\PropertyInfo\Extractor\ReflectionExtractor;
use Symfony\Component\Serializer\Normalizer\DateTimeNormalizer;
use Doctrine\ORM\EntityManagerInterface;

class TimesheetRecordFixtures extends Fixture implements FixtureGroupInterface, OrderedFixtureInterface
{

//    public const DATA_category = [
//        [
//            'parentID' => null,
//            'categoryName' => 'CST',
//            'categoryDescription' => 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore.',
//        ]
//    ];
//    public const DATA_TASK = [
//        [
//
//            'title' => "Homework",
//            'category' => '1'
//        ],
//        [
//
//            'title' => "field trip",
//            'category' => '1'
//        ],
//    ];
    public const DATA_TimesheetRecord = [

        [
            'start' => '2020-01-06T08:00:00-06:00',
            'end' => '2020-01-06T10:00:00-06:00',
            'siteLocation' => 'on-site',
            'comment' => '',
            'title' => 'Prep'
        ],
        [
            'start' => '2020-01-06T10:00:00-06:00',
            'end' => '2020-01-06T12:00:00-06:00',
            'siteLocation' => 'off-site',
            'comment' => '',
            'title' => 'Teaching'
        ],
        [
            'start' => '2020-01-07T08:00:00-06:00',
            'end' => '2020-01-07T15:00:00-06:00',
            'siteLocation' => 'on-site',
            'comment' => '',
            'title' => 'Meetings'
        ],
        [
            'start' => '2020-01-08T09:00:00-06:00',
            'end' => '2020-01-10T15:00:00-06:00',
            'siteLocation' => 'off-site',
            'comment' => '',
            'title' => 'Conference'
        ],
        [
            'start'=> '2020-01-11T09:00:00-06:00',
            'end'=> '2020-01-11T13:00:00-06:00',
            'siteLocation'=>'',
            'comment'=>'',
            'title'=>'Grading',
        ],
        [
            'start' => '2020-01-13T00:00:00-06:00',
            'end' => '2020-01-18T00:00:00-06:00',
            'siteLocation' => 'off-site',
            'comment' => '',
            'title' => 'Vacation'
        ],

        [
            'start'=> '2020-01-14T09:00:00-06:00',
            'end'=> '2020-01-14T13:00:00-06:00',
            'siteLocation'=>'on-site',
            'comment'=>'',
            'title'=>'Grading'
        ],
        [
            'start'=>'2020-01-18T08:00:00-06:00',
            'end'=>'2020-01-18T09:00:00-06:00',
            'siteLocation'=>'on-site',
            'comment'=>'',
            'title'=>'Vacation'
        ],
    ];

    //load the data fixtures to database
    public function load(ObjectManager $manager)
    {
        //for each data set in data fixtures, deserialize the category object to json format, ignore the id value,
        $serializer = new Serializer([new ObjectNormalizer()], [new JsonEncoder()]);


        for( $i=0; $i<count(self::DATA_TimesheetRecord); $i++) {
            $timeData = self::DATA_TimesheetRecord[$i];
            $record = $serializer->deserialize(json_encode($timeData), TimesheetRecord::class, 'json', ['ignored_attributes' => ['taskID', 'recordID', 'title', 'start', 'end', 'personID']]);
            $record->setStart(new \DateTime($timeData['start']));
            $record->setEnd(new \DateTime($timeData['end']));

            if ($i < 6) {
                $record->setPerson($manager->getRepository(Person::class)->findOneBy(array('email' => 'iflynn@hotmail.com')));
            } else {
                $record->setPerson($manager->getRepository(Person::class)->findOneBy(array('email' => 'sfields@hotmail.com')));
            }

            $record->setTask($manager->getRepository(Task::class)->findOneBy(array('title' => $timeData['title'])));
            $manager->persist($record);
        }

        //execute the insert statement to database
        $manager->flush();
    }

    /**
     * This method must return an array of groups
     * on which the implementing class belongs to
     *
     * @return string[]
     */
    public static function getGroups(): array
    {
        return ['timesheetRecord'];
    }

    public function getOrder()
    {
        return 4;
    }
}
