<?php


namespace App\Validator\Constraints;


use Symfony\Component\Validator\Constraint;

/**
 * @Annotation
 * Class PersonCategoryType
 * @package App\Validator\Constraints
 */
class PersonCategoryType extends Constraint
{
    //Constraint message to send with JSON+ld to indicate error
    public $message = "Can't create Person with Task Category";
}