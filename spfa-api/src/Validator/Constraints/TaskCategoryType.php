<?php


namespace App\Validator\Constraints;


use Symfony\Component\Validator\Constraint;

/**
 * @Annotation
 * Class TaskCategoryType
 * @package App\Validator\Constraints
 */
class TaskCategoryType extends Constraint
{
    //Constraint message to send with JSON+ld to indicate error
    public $message = "Can't create Task with Person Category";
}