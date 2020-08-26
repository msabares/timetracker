<?php


namespace App\Entity;

/**
 * Replicates an enumeration class to CategoryTypes
 * Calling this in another class is done by ensuring
 * the other file uses this file, and CategoryType.PERSON
 * or CategoryType.TASK
 * Class CategoryType
 * @package App\Entity
 */
abstract class CategoryType
{
    const PERSON = 1;
    const TASK = 2;
    //const TIMESHEET = 3;
}