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


class TimesheetRecord_Production extends Fixture implements FixtureGroupInterface, OrderedFixtureInterface
{

    /**
     *
     * @param ObjectManager $manager
     */
    public function load(ObjectManager $manager)
    {
        //create a serializer to save the object in stream
        $serializer = new Serializer([new ObjectNormalizer()], [new JsonEncoder()]);
        //Get all the people
        $people = $manager->getRepository(Person::class)->findAll();
        //Get all the tasks
        $tasks = $manager->getRepository(Task::class)->findAll();
        $studentContact = [];
        $compFunc = [];
        $otherAssigned = [];
        $otherNotList = [];

        //Break down the tasks into separate arrays for each category
        $index=0;
        while($index < count($tasks))
        {
            if($tasks[$index]->getCategory()->getCategoryName() == "Student Contact")
            {
                $studentContact[] = $tasks[$index];

            }
            elseif($tasks[$index]->getCategory()->getCategoryName() == "Complementary Function")
            {
                $compFunc[] = $tasks[$index];
            }
            elseif($tasks[$index]->getCategory()->getCategoryName() == "Other Assignments")
            {
                $otherAssigned[] = $tasks[$index];
            }
            else
            {
                $otherNotList[] = $tasks[$index];
            }
            $index++;
        }

        foreach($people as $person)
        {
            $offsetCounter = -180;

            while($offsetCounter <= 0)
            {
                if($offsetCounter % 7 != 0 && strftime('%w',strtotime('sunday last week ' . $offsetCounter.' day')) != 6)
                {
                    $record = new TimesheetRecord();
                    $record->setPerson($person);
                    $record->setTask($this->getRandomTask($studentContact,$compFunc,$otherAssigned,$otherNotList));
                    $record->setSiteLocation($this->getSite());

                    $thisSunday = strftime('%Y-%m-%d', strtotime('sunday last week ' . $offsetCounter.' day'));
                    $startTime = date($thisSunday." ".$this->pickEarlyStart());
                    $endTime = date($thisSunday." ".$this->pickEarlyEnd());
                    $record->setStart(DateTime::createFromFormat('Y-m-d H:i:s',$startTime));
                    $record->setEnd(DateTime::createFromFormat('Y-m-d H:i:s',$endTime));
                    $record->setComment($this->getComment($record));

                    $manager->persist($record);
                }
                $offsetCounter++;
            }
        }

        //execute the insert statement to database
        $manager->flush();


        foreach($people as $person)
        {
            $offsetCounter = -180;

            while($offsetCounter <= 0)
            {
                if($offsetCounter % 7 != 0 && strftime('%w',strtotime('sunday last week ' . $offsetCounter.' day')) != 6 && $person->getEmploymentStatus() == 'full time')
                {
                    $thisSunday = strftime('%Y-%m-%d', strtotime('sunday last week ' . $offsetCounter.' day'));

                    $record2 = new TimesheetRecord();
                    $record2->setPerson($person);
                    $record2->setTask($this->getRandomTask($studentContact,$compFunc,$otherAssigned,$otherNotList));
                    $record2->setSiteLocation($this->getSite());

                    $startTime2 = date($thisSunday." ".$this->pickLateStart());
                    $endTime2 = date($thisSunday." ".$this->pickLateEnd());
                    $record2->setStart(DateTime::createFromFormat('Y-m-d H:i:s',$startTime2));
                    $record2->setEnd(DateTime::createFromFormat('Y-m-d H:i:s',$endTime2));
                    $record2->setComment($this->getComment($record2));

                    $manager->persist($record2);
                }

                $offsetCounter++;
            }
        }

        $manager->flush();
    }

    public static function pickEarlyStart()
    {
        $earlyDayStartTimes = ['07:00:00','07:00:00','07:00:00','07:30:00','08:00:00','08:00:00','08:00:00','08:00:00','08:30:00','09:00:00','09:00:00','09:30:00','10:00:00'];
        return $earlyDayStartTimes[rand(0,12)];
    }

    public static function pickEarlyEnd()
    {
        $earlyDayEndTimes = ['11:30:00','12:00:00','12:00:00','12:00:00','12:00:00','12:30:00','13:00:00'];
        return $earlyDayEndTimes[rand(0,6)];
    }

    public static function pickLateStart()
    {
        $laterDayStartTimes = ['13:00:00','13:00:00','13:00:00','13:00:00','13:30:00','14:00:00'];
        return $laterDayStartTimes[rand(0,5)];
    }

    public static function pickLateEnd()
    {
        $laterDayEndTimes = ['15:00:00','15:00:00','15:00:00','15:30:00','16:00:00','16:00:00','16:00:00','16:30:00','17:00:00','17:30:00','18:00:00'];
        return $laterDayEndTimes[rand(0,10)];
    }

    public static function getRandomTask($studentContact, $compFunc, $otherAssigned, $otherNotList)
    {
        $taskCat = rand(1,10);
        $task = null;
        //Chance of assigning each a task in each category:
        //studentContact 	40%
        //compFunc		 	30%
        //otherAssigned		20%
        //otherNotListed	10%
        switch($taskCat)
        {
            case $taskCat <= 4:
                $task = $studentContact[rand(0,6)];
                break;
            case $taskCat > 9:
                $task = $otherNotList[rand(0,6)];
                break;
            case $taskCat > 4 && $taskCat < 7:
                $task = $compFunc[rand(0,8)];
                break;
            default:
                $task = $otherAssigned[rand(0,10)];
        }

        return $task;
    }

    public static function getSite()
    {
        $site = rand(1,10);
        //Chance of site:
        //onsite			90%
        //offsite			10%
        return $site > 1 ? "on-site" : "off-site";
    }

    public static function getComment(TimesheetRecord $record)
    {

        if($record->getSiteLocation() == "off-site")
        {
            $comment = "Worked from home";
        }
        elseif($record->getTask()->getCategory()->getCategoryName()=="Complementary Function")
        {
            $comment = "Getting things ready for tomorrow and next week";
        }
        else
        {
            $comment = "No comments.";
        }

        return $comment;
    }

    /**
     * This method must return an array of groups
     * on which the implementing class belongs to
     *
     * @return string[]
     */
    public static function getGroups(): array
    {
        return ['demo'];
    }

    public function getOrder()
    {
        return 4;
    }
}
