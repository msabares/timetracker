<?php

namespace App\DataFixtures;

use App\Entity\Category;
use App\Repository\CategoryRepository;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Common\DataFixtures\FixtureInterface;
use Doctrine\Bundle\FixturesBundle\FixtureGroupInterface;
use Doctrine\Common\DataFixtures\OrderedFixtureInterface;
use Doctrine\Common\Persistence\ObjectManager;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class CategoryFixtures extends Fixture implements FixtureGroupInterface, OrderedFixtureInterface
{
    //setup the initial category data for future load
    public static $data = [
        [
            'categoryID' => 1,
            'parentCategory' => null,
            'categoryName' => 'Student Contact',
            'categoryDescription' => 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore.',
            'categoryType' => 2
        ],
        [
            'categoryID' => 2,
            'parentCategory' => null,
            'categoryName' => 'Complementary Function',
            'categoryDescription' => 'Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur.',
            'categoryType' => 2
        ],
        [
            'categoryID' => 3,
            'parentCategory' => null,
            'categoryName' => 'NonInstructional Assignment',
            'categoryDescription' => 'Elit, sed do eiusmod tempor incididunt ut labore sit aspernatur aut odit aut fugit, sed quia consequuntur.',
            'categoryType' => 2
        ],
        [
            'categoryID' => 4,
            'parentCategory' => [
                'categoryID' => 1,
                'parentCategory' => null,
                'categoryName' => 'Student Contact',
                'categoryDescription' => 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore.',
                'categoryType' => 2
            ],
            'categoryName' => 'Assigned Contact Hours',
            'categoryDescription' => 'Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur.',
            'categoryType' => 2
        ],
        [
            'categoryID' => 5,
            'parentCategory' => [
                'categoryID' => 2,
                'parentCategory' => null,
                'categoryName' => 'Complementary Function',
                'categoryDescription' => 'Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur.',
                'categoryType' => 2
            ],
            'categoryName' => 'Admin Tasks',
            'categoryDescription' => 'Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur.',
            'categoryType' => 2
        ],
        [
            'categoryID' => 6,
            'parentCategory' => [
                'categoryID' => 3,
                'parentCategory' => null,
                'categoryName' => 'NonInstructional Assignment',
                'categoryDescription' => 'Elit, sed do eiusmod tempor incididunt ut labore sit aspernatur aut odit aut fugit, sed quia consequuntur.',
                'categoryType' => 2
            ],
            'categoryName' => 'Curriculum Development',
            'categoryDescription' => 'Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur.',
            'categoryType' => 2
        ],
        [
            'categoryID' => 7,
            'parentCategory' => [
                'categoryID' => 3,
                'parentCategory' => null,
                'categoryName' => 'NonInstructional Assignment',
                'categoryDescription' => 'Elit, sed do eiusmod tempor incididunt ut labore sit aspernatur aut odit aut fugit, sed quia consequuntur.',
                'categoryType' => 2
            ],
            'categoryName' => 'Projects',
            'categoryDescription' => 'Integer sit amet tortor a ante aliquet volutpat blandit at leo.',
            'categoryType' => 2
        ],
        [
            'categoryID' => 8,
            'parentCategory' => null,
            'categoryName' => 'Sask Polytech',
            'categoryDescription' => 'The top of the hierarchy',
            'categoryType' => 1
        ],
        [
            'categoryID' => 9,
            'parentCategory' => null,
            'categoryName' => 'Department',
            'categoryDescription' => 'The top of the hierarchy',
            'categoryType' => 1
        ],
        [
            'categoryID' => 10,
            'parentCategory' => [
                'categoryID' => 9,
                'parentCategory' => null,
                'categoryName' => 'Department',
                'categoryDescription' => 'The top of the hierarchy',
                'categoryType' => 1
            ],
            'categoryName' => 'CST',
            'categoryDescription' => 'Data Travellers',
            'categoryType' => 1
        ],
        [
            'categoryID' => 11,
            'parentCategory' => [
                'categoryID' => 9,
                'parentCategory' => null,
                'categoryName' => 'Department',
                'categoryDescription' => 'The top of the hierarchy',
                'categoryType' => 1
            ],
            'categoryName' => 'CADCAM',
            'categoryDescription' => 'Techno Anarchist',
            'categoryType' => 1
        ],
        [
            'categoryID' => 12,
            'parentCategory' => [
                'categoryID' => 9,
                'parentCategory' => null,
                'categoryName' => 'Department',
                'categoryDescription' => 'The top of the hierarchy',
                'categoryType' => 1
            ],
            'categoryName' => 'Nursing',
            'categoryDescription' => 'Healers',
            'categoryType' => 1
        ]
    ];

    //load the data fixtures to database

    public function load(ObjectManager $manager)
    {
        //create a serializer to save the object in stream
        $serializer = new Serializer([new ObjectNormalizer()], [new JsonEncoder()]);
        //for each data set in data fixtures, deserialize the category object to json format, ignore the id value,
        //prepare the category object in sql insert statement
        foreach (self::$data as $catData) {
            $cat = $serializer->deserialize(json_encode($catData), Category::class, 'json', ['ignored_attributes' => ['parentCategory', 'categoryID']]);

            $manager->persist($cat);

        }

        //execute the insert statement to database
        $manager->flush();

        //We go through $data again but we go over every category and set it's parent if applicable. This will also set
        //set the subCategory of the parent's as well.
        foreach (self::$data as $catData) {
            if ($catData['parentCategory'] != null) {

                //Now that we've created all categories in the foreach loop before, we can now make relationships between then.
                //getRepostiory is making a database call to get the existing Category objects in the database for the current object
                //and it's assigned parent.
                $parentCategory = $manager->getRepository(Category::class)->findBy(array('categoryName' => $catData['parentCategory']['categoryName']))[0];
                $currentCategory = $manager->getRepository(Category::class)->findBy(array('categoryName' => $catData['categoryName']))[0];

                //This simply creates the relationship by setting the current category's parent.
                $currentCategory->setParentCategory($parentCategory);

            }
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
        return ['category', 'task', 'timesheetRecord', 'timesheetRecordForFilter'];
    }

    public function getOrder()
    {
        return 1;
    }
}
