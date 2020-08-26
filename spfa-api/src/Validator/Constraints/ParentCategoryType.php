<?php


namespace App\Validator\Constraints;


use Symfony\Component\Validator\Constraint;

/**
 * @Annotation
 * Class ParentCategoryType
 * @package App\Validator\Constraints
 */
class ParentCategoryType extends Constraint
{
    public function getTargets()
    {
        return self::CLASS_CONSTRAINT;
    }

    public $message = 'The Parent Category Type must match the Created Category';
}