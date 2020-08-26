<?php

namespace App\DataFixtures;

use App\Entity\Category;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Common\DataFixtures\OrderedFixtureInterface;
use Doctrine\Common\Persistence\ObjectManager;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Doctrine\Bundle\FixturesBundle\FixtureGroupInterface;

class Category_Production extends Fixture implements FixtureGroupInterface, OrderedFixtureInterface
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
			'categoryDescription' => 'Work to support and complement Student Contact',
			'categoryType' => 2 
		],
		[
			'categoryID' => 3,
			'parentCategory' => null,
			'categoryName' => 'Other Assignments',
			'categoryDescription' => 'Tasks not related to Student Contact',
			'categoryType' => 2 
		],
		[
			'categoryID' => 4,
			'parentCategory' => null,
			'categoryName' => 'Other Not Listed',
			'categoryDescription' => 'Miscellaneous Tasks',
			'categoryType' => 2 
		],
		[
			'categoryID' => 5,
			'parentCategory' => null,
			'categoryName' => 'Department',
			'categoryDescription' => 'Top level category',
			'categoryType' => 1
		],
		[
			'categoryID' => 9,
			'parentCategory' => 		
			[
				'categoryID' => 5,
				'parentCategory' => null,
				'categoryName' => 'Department',
				'categoryDescription' => 'Top level category',
				'categoryType' => 1
			],
			'categoryName' => 'Computer Systems Technology',
			'categoryDescription' => 'CST',
			'categoryType' => 1
		],
		[
			'categoryID' => 10,
			'parentCategory' => 
			[
				'categoryID' => 5,
				'parentCategory' => null,
				'categoryName' => 'Department',
				'categoryDescription' => 'Top level category',
				'categoryType' => 1	
			],
			'categoryName' => 'Computer Aided Design and Manufacturing',
			'categoryDescription' => 'CADCAM',
			'categoryType' => 1
		],
		[
			'categoryID' => 11,
			'parentCategory' => 
			[
				'categoryID' => 5,
				'parentCategory' => null,
				'categoryName' => 'Department',
				'categoryDescription' => 'Top level category',
				'categoryType' => 1	
			],
			'categoryName' => 'Agricultural Equipment Technician',
			'categoryDescription' => 'AgTech',
			'categoryType' => 1
		],
		[
			'categoryID' => 12,
			'parentCategory' => 
			[
				'categoryID' => 5,
				'parentCategory' => null,
				'categoryName' => 'Department',
				'categoryDescription' => 'Top level category',
				'categoryType' => 1	
			],
			'categoryName' => 'Cyber Security',
			'categoryDescription' => 'CSEC',
			'categoryType' => 1
		],
		[
			'categoryID' => 13,
			'parentCategory' =>
			[
				'categoryID' => 5,
				'parentCategory' => null,
				'categoryName' => 'Department',
				'categoryDescription' => 'Top level category',
				'categoryType' => 1	
			],
			'categoryName' => 'Computer Engineering Technology',
			'categoryDescription' => 'CET',
			'categoryType' => 1
		],
		[
			'categoryID' => 14,
			'parentCategory' =>
			[
				'categoryID' => 5,
				'parentCategory' => null,
				'categoryName' => 'Department',
				'categoryDescription' => 'Top level category',
				'categoryType' => 1	
			],
			'categoryName' => 'Electrical Engineering Technology',
			'categoryDescription' => 'EET',
			'categoryType' => 1
		],
		[
			'categoryID' => 15,
			'parentCategory' =>
			[
				'categoryID' => 5,
				'parentCategory' => null,
				'categoryName' => 'Department',
				'categoryDescription' => 'Top level category',
				'categoryType' => 1	
			],
			'categoryName' => 'Building Systems Technician',
			'categoryDescription' => 'BST',
			'categoryType' => 1
		],
		[
			'categoryID' => 16,
			'parentCategory' =>
			[
				'categoryID' => 5,
				'parentCategory' => null,
				'categoryName' => 'Department',
				'categoryDescription' => 'Top level category',
				'categoryType' => 1	
			],
			'categoryName' => 'Dental Assisting',
			'categoryDescription' => 'DAST',
			'categoryType' => 1
		],
		[
			'categoryID' => 17,
			'parentCategory' =>
			[
				'categoryID' => 5,
				'parentCategory' => null,
				'categoryName' => 'Department',
				'categoryDescription' => 'Top level category',
				'categoryType' => 1	
			],
			'categoryName' => 'Graphic Communications',
			'categoryDescription' => 'GCOMM',
			'categoryType' => 1
		],
		[
			'categoryID' => 18,
			'parentCategory' =>
			[
				'categoryID' => 5,
				'parentCategory' => null,
				'categoryName' => 'Department',
				'categoryDescription' => 'Top level category',
				'categoryType' => 1	
			],
			'categoryName' => 'Correctional Studies',
			'categoryDescription' => 'CORR',
			'categoryType' => 1
		],
		[
			'categoryID' => 19,
			'parentCategory' =>
			[
				'categoryID' => 5,
				'parentCategory' => null,
				'categoryName' => 'Department',
				'categoryDescription' => 'Top level category',
				'categoryType' => 1	
			],
			'categoryName' => 'Integrated Resource Management',
			'categoryDescription' => 'IRM',
			'categoryType' => 1
		]
	];
	
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
        return 1;
    }
}