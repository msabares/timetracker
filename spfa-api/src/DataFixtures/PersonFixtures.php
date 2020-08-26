<?php

namespace App\DataFixtures;

use App\Entity\Category;
use App\Entity\Person;
use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Bundle\FixturesBundle\FixtureGroupInterface;
use Doctrine\Common\DataFixtures\OrderedFixtureInterface;
use Doctrine\Common\Persistence\ObjectManager;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class PersonFixtures extends Fixture implements FixtureGroupInterface, OrderedFixtureInterface
{
    const Data_Person=[
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
            'scheduledHours' => 37.5
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
            'scheduledHours' => 20
        ]
        ,
        [
            'personID'=>4,
            'firstName'=>'Michael',
            'lastName'=>'Sabares',
            'email'=>'msabares@hotmail.com',
            'password' => '$2y$13$jCfy3JeLT/dN8ggnhUG9IO0.qSsuNLaPNrDDXCm1cDVOJK/6j4XNW',
            'plainPassword' => 'Sabares123!',
            'category' => 'CST',
            'employmentStatus' => 'full time',
            'scheduledHours' => 20
        ],
        [
            'personID'=>5,
            'firstName'=>'Jordan',
            'lastName'=>'Knodel',
            'email'=>'jknodel@hotmail.com',
            'password' => '$2y$13$blSRz2HTIXVGSiM0.Wp1l.0Ed9ZL6DKqUUfvNmVFoGYmYsXeoBHly',
            'plainPassword' => 'Knodel123!',
            'category' => 'CST',
            'employmentStatus' => 'full time',
            'scheduledHours' => 20
        ],
        [
            'personID'=>6,
            'firstName'=>'Matt',
            'lastName'=>'Smith',
            'email'=>'msmith@hotmail.com',
            'password' => '$2y$13$a5MdI/COpT8TQqgrciyxj.5FILR6eUHVQlmNbTHrs38G68gYegFKW',
            'plainPassword' => 'Smith123!',
            'category' => 'CST',
            'employmentStatus' => 'full time',
            'scheduledHours' => 20
        ],
        [
            'personID'=>7,
            'firstName'=>'Nathan',
            'lastName'=>'Balaniuk',
            'email'=>'nbalaniuk@hotmail.com',
            'password' => '$2y$13$ojDEOHlA6tcotDZPX39lg.s0Wb5y55LRI9SZ0rFBnP.u2Q8d5M.Ti',
            'plainPassword' => 'Balaniuk123!',
            'category' => 'CST',
            'employmentStatus' => 'full time',
            'scheduledHours' => 20
        ],
        [
            'personID'=>8,
            'firstName'=>'Javad',
            'lastName'=>'Neisiany',
            'email'=>'jneisiany@hotmail.com',
            'password' => '$2y$13$r9MV3hUMhc2qBIPDoTaO/.dVgfWGU8EtmDcgZWllSE6Fs45BgQ2JS',
            'plainPassword' => 'Neisiany123!',
            'category' => 'CST',
            'employmentStatus' => 'full time',
            'scheduledHours' => 20
        ]
        ,
        [
            'personID'=>9,
            'firstName'=>'Ying',
            'lastName'=>'Ye',
            'email'=>'yye@hotmail.com',
            'password' => '$2y$13$A9aPBs1XUUqkn8jP/4O52enJjNDRKx9AcX0SebHTxudA43QX2MFT2',
            'plainPassword' => 'Ye12345!',
            'category' => 'CST',
            'employmentStatus' => 'part time',
            'scheduledHours' => 20
        ],
        [
            'personID'=>10,
            'firstName'=>'Luke',
            'lastName'=>'Abell-Smith',
            'email'=>'labellsmith@hotmail.com',
            'password' => '$2y$13$XqZ/.nH34iP/Dj68ZoQcQepmQrzdmxpYbW6JYNY8m50qNfw9YgWlC',
            'plainPassword' => 'AbellSmith123!',
            'category' => 'CST',
            'employmentStatus' => 'full time',
            'scheduledHours' => 20
        ],
        [
            'personID'=>11,
            'firstName'=>'Wade',
            'lastName'=>'Lahoda',
            'email'=>'wlahoda@hotmail.com',
            'password' => '$2y$13$CcPRaSasrgZYR43fuy82LemyykNBaredknczm5RUCy5pzZ0bvIybO',
            'plainPassword' => 'Lahoda123!',
            'category' => 'CST',
            'employmentStatus' => 'full time',
            'scheduledHours' => 20
        ]

    ];
    public function load(ObjectManager $manager)
    {
        $serializer = new Serializer([new ObjectNormalizer()], [new JsonEncoder()]);
        // $product = new Product();
        // $manager->persist($product);
        foreach (self::Data_Person as $personData){
            $person=$serializer->deserialize(json_encode($personData),Person::class,'json', ['ignored_attributes' => ['plainPassword','category']]);
            $person->setCategory($manager->getRepository(Category::class)->findOneByCategoryName($personData['category']));
            $manager->persist(($person));
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
        return ['person', 'timesheetRecord', 'timesheetRecordForFilter','production'];
    }

    /**
     * Get the order of this fixture
     *
     * @return int
     */
    public function getOrder()
    {
        return 2;
    }
}

