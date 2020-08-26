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


class Task_Production extends Fixture implements FixtureGroupInterface, OrderedFixtureInterface
{


    public const DATA_task = [
        [ //1
            'title' => 'Instruction - Class/Lab/Clinical/Shop',
            'category' => 'Student Contact'
        ],
        [
            'title' => 'Work Placement Supervision',
            'category' => 'Student Contact'
        ],
        [
            'title' => 'Field Trips',
            'category' => 'Student Contact'
        ],
        [
            'title' => 'Camps',
            'category' => 'Student Contact'
        ],
        [
            'title' => 'Invigilation',
            'category' => 'Student Contact'
        ],
        [
            'title' => 'Testing',
            'category' => 'Student Contact'
        ],
        [
            'title' => 'Approved Team Teaching Assignments',
            'category' => 'Student Contact'
        ],
        [
            'title' => 'Lesson Planning and Preparation',
            'category' => 'Complementary Function'
        ],
        [
            'title' => 'Student Evaluation, assessment prep, marking, record keeping',
            'category' => 'Complementary Function'
        ],
        [
            'title' => 'Student Meetings, consultation, and advising',
            'category' => 'Complementary Function'
        ],
        [
            'title' => 'Program, school, institutional meetings and work',
            'category' => 'Complementary Function'
        ],
        [
            'title' => 'Ongoing minor curriculum maintenance and updates',
            'category' => 'Complementary Function'
        ],
        [
            'title' => 'Maintaining subject matter expertise',
            'category' => 'Complementary Function'
        ],
        [
            'title' => 'Industry and stakeholder contact',
            'category' => 'Complementary Function'
        ],
        [
            'title' => 'Administrative tasks in support of program delivery',
            'category' => 'Complementary Function'
        ],
        [
            'title' => 'Mentoring of faculty',
            'category' => 'Complementary Function'
        ],
		[
            'title' => 'Other role assignments',
            'category' => 'Other Assignments'
        ],
		[
            'title' => 'Assigned activities',
            'category' => 'Other Assignments'
        ],
		[
            'title' => 'Employer assigned training',
            'category' => 'Other Assignments'
        ],
		[
            'title' => 'Approved professional development',
            'category' => 'Other Assignments'
        ],
		[
            'title' => 'Approved research',
            'category' => 'Other Assignments'
        ],
		[
            'title' => 'Major curriculum development and maintenance',
            'category' => 'Other Assignments'
        ],
		[
            'title' => 'Equipment repair/maintenance',
            'category' => 'Other Assignments'
        ],
		[
            'title' => 'PLAR development',
            'category' => 'Other Assignments'
        ],
		[
            'title' => 'Quality assurance of programs and courses',
            'category' => 'Other Assignments'
        ],
		[
            'title' => 'Assigned travel time',
            'category' => 'Other Assignments'
        ],
		[
            'title' => 'Online/distance courses',
            'category' => 'Other Assignments'
        ],
		[
            'title' => 'BFC course maintenance',
            'category' => 'Other Not Listed'
        ],
		[
            'title' => 'Lead instructor duties',
            'category' => 'Other Not Listed'
        ],
		[
            'title' => 'Email correspondence unrelated to program',
            'category' => 'Other Not Listed'
        ],
		[
            'title' => 'Course and software/hardware research/licensing',
            'category' => 'Other Not Listed'
        ],
		[
            'title' => 'VM Development Environment',
            'category' => 'Other Not Listed'
        ],
		[
            'title' => 'Other (add comment on Record)',
            'category' => 'Other Not Listed'
        ],
		[
            'title' => 'Association business',
            'category' => 'Other Not Listed'
        ]
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
        return ['demo'];
    }

    public function getOrder()
    {
        return 3;
    }
}