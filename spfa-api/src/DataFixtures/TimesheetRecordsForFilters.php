<?php
/**************************************
 * File Name: TaskFixturesProductV2.php
 * User: cst232
 * Date: 2019-11-11/27/2019
 * Project: pmg-api
 *
 *
 **************************************/

namespace App\DataFixtures;

use App\Entity\Person;
use App\Entity\Task;
use App\Entity\TimesheetRecord;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Bundle\FixturesBundle\FixtureGroupInterface;
use Doctrine\Common\DataFixtures\OrderedFixtureInterface;
use Doctrine\Common\Persistence\ObjectManager;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use DateTime;


class TimesheetRecordsForFilters extends Fixture implements FixtureGroupInterface, OrderedFixtureInterface
{
//    public const DATA_person = [
//        [
//            'personID'=>1,
//            'firstName'=>'Bob',
//            'lastName'=>'Smith',
//            'email'=>'BSmith@hotmail.com'
//        ],
//        [
//            'personID'=>2,
//            'firstName'=>'Sienna',
//            'lastName'=>'Fields',
//            'email'=>'SFields@hotmail.com'
//        ],
//        [
//            'personID'=>3,
//            'firstName'=>'Issac',
//            'lastName'=>'Flynn',
//            'email'=>'IFlynn@hotmail.com'
//        ]
//    ];
    public const DATA_TimesheetRecord = [
        ////THIS WEEK
        [// Monday, Prep, 0800-1000
            'start' => '08:00:00',
            'end' => '10:00:00',
            'siteLocation' => 'on-site',
            'comment' => '',
            'title' => 'Prep',
            'dateOffset' => '+1'
        ],
        [// Monday, Teaching 1000-1200
            'start' => '10:00:00',
            'end' => '12:00:00',
            'siteLocation' => 'off-site',
            'comment' => '',
            'title' => 'Teaching',
            'dateOffset' => '+1'
        ],
        [// Monday, Teaching 1300-1500
            'start' => '13:00:00',
            'end' => '15:00:00',
            'siteLocation' => 'off-site',
            'comment' => '',
            'title' => 'Teaching',
            'dateOffset' => '+1'
        ],
        [// Tuesday, Meetings 1500-1600
            'start' => '15:00:00',
            'end' => '16:00:00',
            'siteLocation' => 'off-site',
            'comment' => '',
            'title' => 'Meetings',

            'dateOffset' => '+2'
        ],
        [// Thursday, Teaching 0800-1000
            'start' => '08:00:00',
            'end' => '10:00:00',
            'siteLocation' => 'off-site',
            'comment' => '',
            'title' => 'Teaching',
            'dateOffset' => '+4'
        ],
        [// Thursday, Prep, 1000-1200
            'start' => '10:00:00',
            'end' => '12:00:00',
            'siteLocation' => 'on-site',
            'comment' => '',
            'title' => 'Prep',
            'dateOffset' => '+4'
        ],
        [// Thursday, Teaching 1300-1500
            'start' => '13:00:00',
            'end' => '15:00:00',
            'siteLocation' => 'off-site',
            'comment' => '',
            'title' => 'Teaching',
            'dateOffset' => '+4'
        ],
        [// Friday, Grading 1300-1500
            'start' => '13:00:00',
            'end' => '15:00:00',
            'siteLocation' => 'on-site',
            'comment' => '',
            'title' => 'Grading',
            'dateOffset' => '+5'
        ],
        [// Saturday, Grading 0900-1300
            'start' => '09:00:00',
            'end' => '13:00:00',
            'siteLocation' => 'off-site',
            'comment' => '',
            'title' => 'Grading',
            'dateOffset' => '+6'
        ],
        ////LAST WEEK
        [// Wednesday, Development 0800-1600
            'start' => '08:00:00',
            'end' => '16:00:00',
            'siteLocation' => 'on-site',
            'comment' => '',
            'title' => 'Development',
            'dateOffset' => '-4'
        ],
        [// Wednesday, Conference 0800-1600
            'start' => '08:00:00',
            'end' => '16:00:00',
            'siteLocation' => 'off-site',
            'comment' => '',
            'title' => 'Conference',
            'dateOffset' => '-2'
        ],
        ////TWO WEEKS AGO
        [// Monday, Prep, 0800-1000
            'start' => '08:00:00',
            'end' => '10:00:00',
            'siteLocation' => 'on-site',
            'comment' => '',
            'title' => 'Prep',
            'dateOffset' => '-13'
        ],
        [// Monday, Teaching 1000-1200
            'start' => '10:00:00',
            'end' => '12:00:00',
            'siteLocation' => 'off-site',
            'comment' => '',
            'title' => 'Teaching',
            'dateOffset' => '-13'
        ],
        [// Monday, Teaching 1300-1500
            'start' => '13:00:00',
            'end' => '15:00:00',
            'siteLocation' => 'off-site',
            'comment' => '',
            'title' => 'Teaching',
            'dateOffset' => '-13'
        ],
        [// Tuesday, Long title 1300-1500
            'start' => '13:00:00',
            'end' => '15:00:00',
            'siteLocation' => 'on-site',
            'comment' => '',
            'title' => 'Here is another long task name, but in actual words and not just M\'s',
            'dateOffset' => '-12'
        ],
        [// Tuesday, Meetings 1500-1600
            'start' => '15:00:00',
            'end' => '16:00:00',
            'siteLocation' => 'on-site',
            'comment' => '',
            'title' => 'Meetings',
            'dateOffset' => '-12'
        ],
        [// Wednesday, Development 0800-1000
            'start' => '08:00:00',
            'end' => '10:00:00',
            'siteLocation' => 'off-site',
            'comment' => '',
            'title' => 'Development',
            'dateOffset' => '-11'
        ],
        [// Friday, Grading 1300-1500
            'start' => '13:00:00',
            'end' => '15:00:00',
            'siteLocation' => 'on-site',
            'comment' => '',
            'title' => 'Grading',
            'dateOffset' => '-11'
        ],
        [// Thursday, Conference 0800-1600
            'start' => '08:00:00',
            'end' => '16:00:00',
            'siteLocation' => 'off-site',
            'comment' => '',
            'title' => 'Conference',
            'dateOffset' => '-10'
        ],
        [// Friday, Vacation 0800-1700
            'start' => '08:00:00',
            'end' => '17:00:00',
            'siteLocation' => 'off-site',
            'comment' => '',
            'title' => 'Vacation',
            'dateOffset' => '-9'
        ],
    ];

    /**
     *
     * @param ObjectManager $manager
     */
    public function load(ObjectManager $manager)
    {
        //create a serializer to save the object in stream
        $serializer = new Serializer([new ObjectNormalizer()], [new JsonEncoder()]);

        foreach (self::DATA_TimesheetRecord as $timeData) {
            $record = $serializer->deserialize(json_encode($timeData), TimesheetRecord::class, 'json', ['ignored_attributes' =>
                ['taskID', 'recordID', 'title', 'start', 'end', 'taskNo']]);

            $thisSunday = strftime('%Y-%m-%d', strtotime('sunday last week ' . $timeData['dateOffset'] . ' day'));
            $startDateTime = date($thisSunday . " " . $timeData['start']);
            $endDateTime = date($thisSunday . " " . $timeData['end']);


            $record->setStart(DateTime::createFromFormat('Y-m-d H:i:s', $startDateTime));
            $record->setEnd(DateTime::createFromFormat('Y-m-d H:i:s', $endDateTime));
            $record->setPerson($manager->getRepository(Person::class)->findOneBy(array('email'=>'iflynn@hotmail.com')));
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
        return ['timesheetRecordForFilter'];
    }

    public function getOrder()
    {
        return 3;
    }
}
