<?php


namespace App\Validator\Constraints;

use Symfony\Component\Validator\Constraint;
use Symfony\Component\Validator\ConstraintValidator;

/**
 * @Annotation
 * Class ParentCategoryTypeValidator
 * @package App\Validator\Constraints
 * Provides validation for Categories, to prevent a Category of one type
 * assigning a Parent Category of a different type
 */
final class ParentCategoryTypeValidator extends ConstraintValidator
{

    /**
     * Checks if the passed value is valid.
     *
     * @param mixed $value The value that should be validated
     */
    public function validate($value, Constraint $constraint)
    {
        //If the Parent Category is null, no need to validate
        if($value->getParentCategory() == null || $value->getParentCategory() == "")
        {
            return;
        }

        //Ensure category type and parent category types are equal
        if($value->getCategoryType() != $value->getParentCategory()->getCategoryType())
        {
            //Otherwise add violation message from ParentCategoryType
            $this->context->buildViolation($constraint->message)->atPath('parentCategory')->addViolation();
        }
    }
}