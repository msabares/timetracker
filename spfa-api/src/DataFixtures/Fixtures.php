<?php
/**************************************
 * File Name: TaskFixtures.php
 * User: cst232
 * Date: 2019-11-11/27/2019
 * Project: pmg-api
 *
 *
 **************************************/

namespace App\DataFixtures;

use App\Entity\Category;
use App\Entity\Person;
use App\Entity\Task;
use App\Entity\TimesheetRecord;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Bundle\FixturesBundle\FixtureGroupInterface;
use Doctrine\Common\Persistence\ObjectManager;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Validator\Constraints\DateTime;
use DateTimeZone;

class Fixtures extends Fixture implements FixtureGroupInterface
{
    public const DATA_category = [
        [
            'categoryName' => 'Development',
        ],
        [
            'categoryName' => 'Student Contact',
        ],
        [
            'categoryName' => 'Administrative',
        ],
        [
            'categoryName' => 'Complementary Function',
        ],
        [
            'categoryName' => 'NonInstructional Assignment',
        ]
    ];


    public const DATA_person = [
        [
            'personID'=>1,
            'firstName'=>'Bob',
            'lastName'=>'Smith',
            'email'=>'bsmith@hotmail.com',
            'password'=>'$argon2i$v=19$m=65536,t=4,p=1$YmRFNWJlT1UySWtSdkdZQg$kBqRCE1WFWDGbXOm3WEJswQbSJ3lhBgln0FnInBb+FQ', //Bob1234!
            'plainPassword' => 'Bob1234!',
            'category' => 'CST',
            'employmentStatus' => 'casual',
            'scheduledHours' => 10,
            'roles' => ['ROLE_ADMIN']
        ],
        [
            'personID'=>2,
            'firstName'=>'Sienna',
            'lastName'=>'Fields',
            'email'=>'sfields@hotmail.com',
            'password' => '$2y$13$rObsYmQQgRUMqvj5jox9OesCg4vZcMWYzqpSUFph3RdvQF/r6F6Fq',
            'plainPassword' => 'Fields123!',
            'category' => 'CADCAM',
            'employmentStatus' => 'full time',
            'scheduledHours' => 37.5, 'roles' => ['ROLE_USER']

        ],
        [
            'personID'=>3,
            'firstName'=>'Issac',
            'lastName'=>'Flynn',
            'email'=>'iflynn@hotmail.com',
            'password' => '$2y$13$rtW790NqY95AAwm7lbH60.fKKwI6elkPWf02H56bsfVZHqjqAefbq',
            'plainPassword' => 'Flynn123!',
            'category' => 'Nursing',
            'employmentStatus' => 'part time',
            'scheduledHours' => 20, 'roles' => ['ROLE_USER']
        ]
    ];


    public const DATA_task = [
        [
            'title' => 'Development',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 5.0,
            'category' => 1
        ],
        [
            'title' => 'Plumbing',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 1
        ],
        [
	       'title'=>'A',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'B',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'C',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'D',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'E',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'F',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'G',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'H',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'I',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'J',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'K',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'L',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'M',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'N',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'O',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'P',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'Q',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'R',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'S',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'T',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
	       'title'=>'U',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ],
        [
            'title' => 'V',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 1
        ],
        [
	       'title'=>'W',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ]
        ,
        [
	       'title'=>'X',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ]
        ,
        [
	       'title'=>'Y',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>12.0,
	       'category'=>1

        ]
        ,
        [
	       'title'=>'Z',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>200.0,
	       'category'=>1

        ],
        [
	       'title'=>'MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM',
	       'hoursEstimate'=>5.0,
	       'hoursSpent'=>200.0,
	       'category'=>1

        ],
        [
	        'title'=>'Here is another long task name, but in actual words and not just M\'s',
	        'hoursEstimate'=>5.0,
	        'hoursSpent'=>200.0,
	        'category'=>1

        ],
        [
            'title' => 'Prep',
        ],
        [
            'title' => 'Teaching',
        ],
        [
            'title' => 'Meetings',
        ],
        [
            'title' => 'Conference',
        ],
        [
            'title'=>'Grading Papers',
        ],
        [
            'title' => 'Vacation',
        ]

    ];

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
            'comment' => 'Teaching Math',
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
            'comment'=>'MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM',
            'title'=>'Grading PapersAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA',
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

    /**
     * Loads the Category fixture first as Task is
     * dependent on it.
     * Then builds Task fixture setting each task
     *
     * @param ObjectManager $manager
     */
    public function load(ObjectManager $manager)
    {
        //create a serializer to save the object in stream
        $serializer = new Serializer([new ObjectNormalizer()], [new JsonEncoder()]);
        //for each data set in data fixtures, deserialize the category object to json format, ignore the id value,
        //prepare the category object in sql insert statement
        foreach (self::DATA_category as $catData) {
            $cat = $serializer->deserialize(json_encode($catData), Category::class, 'json', ['ignored_attributes' => ['categoryID']]);
            $manager->persist($cat);
        }
        //execute the insert statement to database
        $manager->flush();

        //Loop through each Task the taskData
        //Create a new Task each iteration, and set its
        //properties from taskData
        //Persist then flush after all completed
        $tasksArray = [];
        foreach (self::DATA_task as $taskData) {
            $task = new Task();
            $task->setTitle($taskData['title']);
            $task->setCategory($cat);
            $manager->persist($task);
            $tasksArray[] = $task;
        }
        $manager->flush();

        $personArray = [];
        foreach(self::DATA_person as $personData) {
            $person=$serializer->deserialize(json_encode($personData),Person::class,'json', ['ignored_attributes' => ['plainPassword','category']]);
            $person->setCategory($manager->getRepository(Category::class)->findOneByCategoryName($personData['category']));
            $manager->persist(($person));
            $personArray[]=$person;
        }
        $manager->flush();


        $arrayIndex = 30;

        for($i=0;$i<8;$i++){
            $timeData=self::DATA_TimesheetRecord[$i];
            $record = $serializer->deserialize(json_encode($timeData),TimesheetRecord::class, 'json',['ignored_attributes' => ['taskID','recordID','title','start','end','personID']]);
            $record->setStart(new \DateTime($timeData['start']));
            $record->setEnd(new \DateTime($timeData['end']));
            if($i<6)
            {$record->setPerson($personArray[2]);
             $record->setTask($tasksArray[$arrayIndex+$i]);}
            else{
                $record->setPerson($personArray[1]);
                $record->setTask($tasksArray[$arrayIndex-$i]);
            }
            //$record->setTask($manager->getRepository(Task::class)->find($timeData['taskID']));
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
        return ['group1'];
    }
}
