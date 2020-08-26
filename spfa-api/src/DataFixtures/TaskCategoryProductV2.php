<?php

namespace App\DataFixtures;

use App\Entity\Category;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Common\Persistence\ObjectManager;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Doctrine\Bundle\FixturesBundle\FixtureGroupInterface;

class TaskCategoryProductV2 extends Fixture implements FixtureGroupInterface
{
    public static $data = [
        [
            'categoryID' => 1,
            'parentCategory' => null,
            'categoryName' => 'Student Contact',
            'categoryDescription' => 'Assigned direct contact with students',
            'categoryType' => 2
        ],
        [
            'categoryID' => 2,
            'parentCategory' => null,
            'categoryName' => 'Complementary Function',
            'categoryDescription' => 'Work performed in support of instructional duties and regular program operations, which is re
                                    required by an employee to provide quality programming.',
            'categoryType' => 2
        ],
        [
            'categoryID' => 3,
            'parentCategory' => null,
            'categoryName' => 'Other Assignments',
            'categoryDescription' => 'Specific assigned activities outside of he scope of complementary function.',
            'categoryType' => 2
        ],
        [
            'categoryID' => 4,
            'parentCategory' => [
                'categoryName' => 'Student Contact',
            ],
            'categoryName' => 'instruction',
            'categoryType' => 2
        ],
        [
            'categoryID' => 5,
            'parentCategory' => [
                'categoryName' => 'instruction',
            ],
            'categoryName' => 'class',
            'categoryType' => 2
        ],
        [
             'categoryID' => 6,
            'parentCategory' => [
                'categoryName' => 'instruction',
             ],
            'categoryName' => 'lab',
            'categoryType' => 2
        ],
        [

            'categoryID' => 7,
            'parentCategory' => [
                 'categoryName' => 'instruction',
            ],
            'categoryName' => 'clinical',
            'categoryType' => 2

        ],
        [
            'categoryID' => 8,
            'parentCategory' => [
                'categoryName' => 'instruction',
            ],
            'categoryName' => 'shop',
            'categoryType' => 2
        ],
        [
            'categoryID' => 9,
            'parentCategory' => [
                'categoryName' => 'Complementary Function',
            ],
            'categoryName' => 'Lesson planing & course/materials/equipment preparation related to column A',
            'categoryType' => 2
        ],
        [
            'categoryID' => 10,
            'parentCategory' => [
                'categoryName' => 'Other Assignments',
            ],
            'categoryName' => 'assigned activities',
            'categoryType' => 2
        ],
        [
            'categoryID' => 11,
            'parentCategory' => null,
            'categoryName' => 'Sask Polytech',
            'categoryDescription' => 'The top of the hierarchy',
            'categoryType' => 1
        ],
        [
            'categoryID' => 12,
            'parentCategory' => null,
            'categoryName' => 'NonInstructional Assignment',
            'categoryDescription' => 'The top of the hierarchy',
            'categoryType' => 2
        ],
        [
            'categoryID' => 13,
            'parentCategory' => [
                'categoryName' => 'NonInstructional Assignment',
            ],
            'categoryName' => 'Assigned Contact Hours',
            'categoryType' => 2
        ],
        [
            'categoryID' => 14,
            'parentCategory' => null,
            'categoryName' => 'Department',
            'categoryDescription' => 'The top of the hierarchy',
            'categoryType' => 1
        ],
        [
            'categoryID' => 15,
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
            'categoryID' => 16,
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
            'categoryID' => 17,
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
    public function load(ObjectManager $manager)
    {
        // $product = new Product();
        // $manager->persist($product);
        //create a serializer to save the object in stream
        $serializer = new Serializer([new ObjectNormalizer()], [new JsonEncoder()]);
        //for each data set in data fixtures, deserialize the category object to json format, ignore the id value,
        //prepare the category object in sql insert statement
        foreach (self::$data as $catData) {
            $cat = $serializer->deserialize(json_encode($catData), Category::class, 'json', ['ignored_attributes' => ['parentCategory', 'categoryID']]);

            $manager->persist($cat);

        }

        $manager->flush();

        foreach (self::$data as $catData) {
            if ($catData['parentCategory'] != null) {
                $parentCategory = $manager->getRepository(Category::class)->findOneBy(array('categoryName' => $catData['parentCategory']['categoryName']));
                $currentCategory = $manager->getRepository(Category::class)->findBy(array('categoryName' => $catData['categoryName']))[0];

                $currentCategory->setParentCategory($parentCategory);

            }
        }
        $manager->flush();
    }

    public static function getGroups(): array
    {
        return ['production'];
    }

    public function getOrder()
    {
        return 1;
    }
}
