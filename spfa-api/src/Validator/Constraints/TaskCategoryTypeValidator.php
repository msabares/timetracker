<?php


namespace App\Validator\Constraints;


use App\Entity\CategoryType;
use Symfony\Component\Validator\Constraint;
use Symfony\Component\Validator\ConstraintValidator;

/**
 * @Annotation
 * Class TaskCategoryTypeValidator
 * @package App\Validator\Constraints
 */
final class TaskCategoryTypeValidator extends ConstraintValidator
{

    /**
     * Checks if the passed value is valid.
     * Ensure that VALUE is the proper Category Type for a TASK entity
     * @param mixed $value The value that should be validated
     */
    public function validate($value, Constraint $constraint)
    {
        //Verify the correct category type for task
        if($value->getCategoryType() != CategoryType::TASK )
        {
            //If wrong type, add the message from TaskCategoryType constraint
            //to JSON+ld violation collection
            $this->context->buildViolation($constraint->message)->addViolation();
        }
    }
}