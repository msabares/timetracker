<?php


namespace App\EventListener;

use Lexik\Bundle\JWTAuthenticationBundle\Event\JWTCreatedEvent;
use ApiPlatform\Core\Api\IriConverterInterface;

class JWTCreatedListener
{
    //When JWT token created, set the data include user IRI info.
    public function onJWTCreated(JWTCreatedEvent $event) : void
    {
        //get data from JWT token
        $payload=$event->getData();
        //get User from JWT token
        $user = $event->getUser();

        //set person iri into token 'person' property
        $payload['person'] = '/api/people/'.$user->getPersonID();
        //set the person name into the token
        $payload['firstName'] = $user->getFirstName();
        $payload['lastName'] = $user->getLastName();
        $payload['categoryName'] = $user->getCategory()->getCategoryName();
        $payload['scheduledHours'] = $user->getScheduledHours();
        $payload['employmentStatus'] = $user->getEmploymentStatus();

        $event->setData($payload);
    }
}