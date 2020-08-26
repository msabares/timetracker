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

class Person_Production extends Fixture implements FixtureGroupInterface, OrderedFixtureInterface
{
    const Data_Person=[
        [
            'personID'=>1,
            'firstName'=>'Bob',
            'lastName'=>'Smith',
            'email'=>'bsmith@hotmail.com',
            'password'=>'$argon2i$v=19$m=65536,t=4,p=1$YmRFNWJlT1UySWtSdkdZQg$kBqRCE1WFWDGbXOm3WEJswQbSJ3lhBgln0FnInBb+FQ', //Bob1234!
            'plainPassword' => 'Bob1234!',
            'category' => 'Electrical Engineering Technology',
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
            'category' => 'Building Systems Technician',
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
            'category' => 'Dental Assisting',
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
            'category' => 'Computer Systems Technology',
            'employmentStatus' => 'full time',
            'scheduledHours' => 37.5
        ],
        [
            'personID'=>5,
            'firstName'=>'Jordan',
            'lastName'=>'Knodel',
            'email'=>'jknodel@hotmail.com',
            'password' => '$2y$13$blSRz2HTIXVGSiM0.Wp1l.0Ed9ZL6DKqUUfvNmVFoGYmYsXeoBHly',
            'plainPassword' => 'Knodel123!',
            'category' => 'Computer Systems Technology',
            'employmentStatus' => 'full time',
            'scheduledHours' => 37.5
        ],
        [
            'personID'=>6,
            'firstName'=>'Matt',
            'lastName'=>'Smith',
            'email'=>'msmith@hotmail.com',
            'password' => '$2y$13$a5MdI/COpT8TQqgrciyxj.5FILR6eUHVQlmNbTHrs38G68gYegFKW',
            'plainPassword' => 'Smith123!',
            'category' => 'Agricultural Equipment Technician',
            'employmentStatus' => 'full time',
            'scheduledHours' => 37.5
        ],
        [
            'personID'=>7,
            'firstName'=>'Nathan',
            'lastName'=>'Balaniuk',
            'email'=>'nbalaniuk@hotmail.com',
            'password' => '$2y$13$ojDEOHlA6tcotDZPX39lg.s0Wb5y55LRI9SZ0rFBnP.u2Q8d5M.Ti',
            'plainPassword' => 'Balaniuk123!',
            'category' => 'Computer Aided Design and Manufacturing',
            'employmentStatus' => 'full time',
            'scheduledHours' => 37.5
        ],
        [
            'personID'=>8,
            'firstName'=>'Javad',
            'lastName'=>'Neisiany',
            'email'=>'jneisiany@hotmail.com',
            'password' => '$2y$13$r9MV3hUMhc2qBIPDoTaO/.dVgfWGU8EtmDcgZWllSE6Fs45BgQ2JS',
            'plainPassword' => 'Neisiany123!',
            'category' => 'Agricultural Equipment Technician',
            'employmentStatus' => 'full time',
            'scheduledHours' => 37.5
        ]
        ,
        [
            'personID'=>9,
            'firstName'=>'Ying',
            'lastName'=>'Ye',
            'email'=>'yye@hotmail.com',
            'password' => '$2y$13$A9aPBs1XUUqkn8jP/4O52enJjNDRKx9AcX0SebHTxudA43QX2MFT2',
            'plainPassword' => 'Ye12345!',
            'category' => 'Computer Aided Design and Manufacturing',
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
            'category' => 'Cyber Security',
            'employmentStatus' => 'full time',
            'scheduledHours' => 37.5
        ],
        [
            'personID'=>11,
            'firstName'=>'Wade',
            'lastName'=>'Lahoda',
            'email'=>'wlahoda@hotmail.com',
            'password' => '$2y$13$CcPRaSasrgZYR43fuy82LemyykNBaredknczm5RUCy5pzZ0bvIybO',
            'plainPassword' => 'Lahoda123!',
            'category' => 'Cyber Security',
            'employmentStatus' => 'full time',
            'scheduledHours' => 37.5
        ],
        [
            'personID'=>12,
            'firstName'=>'Elliot',
            'lastName'=>'Alderson',
            'email'=>'ealderson@gmail.com',
            'password' => '$2y$13$tDUtHDyylDBkFx9KAG.NsuF0HlIKPtiS4m10enyxh8AyeKE0gHxEm',
            'plainPassword' => 'Alderson123!',
            'category' => 'Computer Systems Technology',
            'employmentStatus' => 'full time',
            'scheduledHours' => 37.5
        ],
        [
            'personID'=>13,
            'firstName'=>'Darlene',
            'lastName'=>'Alderson',
            'email'=>'dalderson@gmail.com',
            'password' => '$2y$13$Htx/FmoXBMATSw3utqx6Wui/UGKIhvf0fb8FrAF.iIWFMaQNkGRaq',
            'plainPassword' => 'Alderson123!!',
            'category' => 'Cyber Security',
            'employmentStatus' => 'full time',
            'scheduledHours' => 37.5
        ],
        [
            'personID'=>14,
            'firstName'=>'Angela',
            'lastName'=>'Moss',
            'email'=>'amoss@yahoo.ca',
            'password' => '$2y$13$xXtsxuU0K/wQfJUuKSsPrO8F9p/uQSW0tidxBi8RclNa0i73x36ga',
            'plainPassword' => 'Moss123!',
            'category' => 'Computer Systems Technology',
            'employmentStatus' => 'part time',
            'scheduledHours' => 20
        ],
        [
            'personID'=>15,
            'firstName'=>'Tyrell',
            'lastName'=>'Wellick',
            'email'=>'twellick@gmail.com',
            'password' => '$2y$13$ICkkMJL.C57IG2pkZy3zf.Kn/qPUgzVEspBzXG2cpyYF1O1ygjeXC',
            'plainPassword' => 'Wellick1!',
            'category' => 'Computer Systems Technology',
            'employmentStatus' => 'full time',
            'scheduledHours' => 37.5
        ],
        [
            'personID'=>16,
            'firstName'=>'Joanna',
            'lastName'=>'Wellick',
            'email'=>'jwellic@gmail.com',
            'password' => '$2y$13$E29V9Fn0XaUd8jv7lgGnMexFjNWYRi7p37pO34NRBUQPZjIUSxNAi',
            'plainPassword' => 'Wellick1!!',
            'category' => 'Computer Engineering Technology',
            'employmentStatus' => 'part time',
            'scheduledHours' => 20
        ],
        [
            'personID'=>17,
            'firstName'=>'Phillip',
            'lastName'=>'Price',
            'email'=>'pprice@yahoo.ca',
            'password' => '$2y$13$s/YjQrKAaYHZ/dzQD8h1ZuQIGkuTLy5IWXEgy2330YIN2ATLxxa0W',
            'plainPassword' => 'Price12!',
            'category' => 'Electrical Engineering Technology',
            'employmentStatus' => 'part time',
            'scheduledHours' => 28
        ],
        [
            'personID'=>18,
            'firstName'=>'Gideon',
            'lastName'=>'Goddard',
            'email'=>'ggoddard@hotmail.com',
            'password' => '$2y$13$Emv0U3u7qUsCiTM1aCKvbuVjpW5bGSE/KO.ZDAZGhFbJ1odlsFWD6',
            'plainPassword' => 'Goddard1!',
            'category' => 'Building Systems Technician',
            'employmentStatus' => 'part time',
            'scheduledHours' => 32
        ],
        [
            'personID'=>19,
            'firstName'=>'Lloyd',
            'lastName'=>'Chung',
            'email'=>'chung@hotmail.com',
            'password' => '$2y$13$1SqGvRYijbcOytUSNSjLIO.KFbwq6w/I1DvSue2Ys5zfACEnYxWLS',
            'plainPassword' => 'Chung123!',
            'category' => 'Graphic Communications',
            'employmentStatus' => 'full time',
            'scheduledHours' => 37.5
        ],
        [
            'personID'=>20,
            'firstName'=>'Ollie',
            'lastName'=>'Parker',
            'email'=>'oparker@yahoo.ca',
            'password' => '$2y$13$hp494FCqHb/OnTrEu8lRpuBKKrxNd4Hqacbh7VLRvM6lQBgzm.qxG',
            'plainPassword' => 'Parker12!',
            'category' => 'Correctional Studies',
            'employmentStatus' => 'full time',
            'scheduledHours' => 37.5
        ],
        [
            'personID'=>21,
            'firstName'=>'Krista',
            'lastName'=>'Gordon',
            'email'=>'kgordon@gmail.com',
            'password' => '$2y$13$1OL3tqFLkXBk3KcBNiayKOdJMB1B6DvbWABqlv2IAunj9JIEMO/nG',
            'plainPassword' => 'Gordon1!',
            'category' => 'Integrated Resource Management',
            'employmentStatus' => 'full time',
            'scheduledHours' => 37.5
        ],
        [
            'personID'=>22,
            'firstName'=>'Shayla',
            'lastName'=>'Nico',
            'email'=>'snico@hotmail.com',
            'password' => '$2y$13$Ok4gjhRVD39g9iD2A.P6Je33CPWqXn9m1auB3fUND1sFWpvDAKn8e',
            'plainPassword' => 'Nico123!',
            'category' => 'Graphic Communications',
            'employmentStatus' => 'full time',
            'scheduledHours' => 37.5
        ],
        [
            'personID'=>23,
            'firstName'=>'Fernando',
            'lastName'=>'Vera',
            'email'=>'fvera@yahoo.ca',
            'password' => '$2y$13$ManWQgS0t5pfCzp/lAI.GO2jzSUJhI.eHURC7AA9A7FBVGvYmXbl6',
            'plainPassword' => 'Vera123!',
            'category' => 'Dental Assisting',
            'employmentStatus' => 'part time',
            'scheduledHours' => 16
        ],
        [
            'personID'=>24,
            'firstName'=>'Scott',
            'lastName'=>'Knowles',
            'email'=>'sknowles@hotmail.com',
            'password' => '$2y$13$kqVGm.j4/hn5iU.G7QqM2OFGdxhJYyBCfLtiHFKfquewkXkTihnsy',
            'plainPassword' => 'Knowles1!',
            'category' => 'Building Systems Technician',
            'employmentStatus' => 'full time',
            'scheduledHours' => 37.5
        ],
        [
            'personID'=>25,
            'firstName'=>'Zhi',
            'lastName'=>'Zhang',
            'email'=>'zzhang@gmail.com',
            'password' => '$2y$13$620FbjV0IENuxolh8IOXeeCbri.iSZNuCZNpVoUVgu5XyEcqqfzxy',
            'plainPassword' => 'Zhang12!',
            'category' => 'Computer Systems Technology',
            'employmentStatus' => 'full time',
            'scheduledHours' => 37.5
        ],
    

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
        return ['demo'];
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

