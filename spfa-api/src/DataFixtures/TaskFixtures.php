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
use App\Entity\Task;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Bundle\FixturesBundle\FixtureGroupInterface;
use Doctrine\Common\DataFixtures\OrderedFixtureInterface;
use Doctrine\Common\Persistence\ObjectManager;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\JsonSerializableNormalizer;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;


class TaskFixtures extends Fixture implements FixtureGroupInterface, OrderedFixtureInterface
{


    public const DATA_task = [
        [ //1
            'title' => 'Teaching',
            'category' => 'Student Contact'
        ],
        [ //2
            'title' => 'Prep',
            'category' => 'Curriculum Development'
        ],
        [ //3
            'title' => 'Grading',
            'category' => 'NonInstructional Assignment'
        ],
        [ //4
            'title' => 'Meetings',
            'category' => 'NonInstructional Assignment'
        ],
        [ //5
            'title' => 'Conference',
            'category' => 'NonInstructional Assignment'
        ],
        [ //6
            'title' => 'Vacation',
            'category' => 'Complementary Function'
        ],
        [
            'title' => 'Development',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 5.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'Plumbing',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'A',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'B',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'C',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'D',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'E',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'F',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'G',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'H',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'I',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'J',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'K',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'L',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'M',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'N',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'O',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'P',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'Q',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'R',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'S',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'T',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'U',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'V',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'W',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'X',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'Y',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 12.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'Z',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 200.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 200.0,
            'category' => 'Assigned Contact Hours',
        ],
        [
            'title' => 'Here is another long task name, but in actual words and not just M\'s',
            'hoursEstimate' => 5.0,
            'hoursSpent' => 200.0,
            'category' => 'Assigned Contact Hours',
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

        //Loop through each Task the taskData
        //Create a new Task each iteration, and set its
        //properties from taskData
        //Persist then flush after all completed
        foreach (self::DATA_task as $taskData) {
            $task = $serializer->deserialize(json_encode($taskData), Task::class, 'json', ['ignored_attributes' => ['category']]);
            $task->setCategory($manager->getRepository(Category::class)->findOneByCategoryName($taskData['category']));
            $manager->persist($task);
        }
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
        return ['task', 'timesheetRecord', 'timesheetRecordForFilter'];
    }

    public function getOrder()
    {
        return 3;
    }
}