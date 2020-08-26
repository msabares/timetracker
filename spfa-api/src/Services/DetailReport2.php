<?php


namespace App\Services;


use App\Repository\CategoryRepository;

class DetailReport2
{
    public function __construct()
    {
    }

    public function buildDetailReport2($start,$end,$data,$cats,$scheduled)//: array
    {
        //Array to return to the controller
        $result = [];
        //List of top-level category names to use for validation
        $catNames = [];
        //Accumulate hours for all records to return
        $totalHours = 0;
        //Loop through each categoryName passed in
        //and add to catNames for validation
        //Start building the result structure here
        //If we have trusty data, we can probably get rid of this
        foreach($cats as $cat)
        {
            $catNames[] = $cat['categoryName'];
            $result[] = ['category' => $cat['categoryName'],'count' => 0, 'data' => []];
        }
        //Loop through each record from data passed in
        foreach ($data as $record)
        {
            //Get the category name
            $catName = $record['categoryName'];
            //Ensure category name is on the list of top-level categories
            //This rules out poorly structured categories from sneaking through
            //i.e. at this time there should ONLY be top level categories for
            //record tasks. If we have trusty data, we can probably get rid of this
            if(in_array($catName,$catNames))
            {
                $taskExists = false;  //Flag to check if the task is already in results
                $catFound = false; //Flag to check if category is already in results
                $taskTitle = $record['title']; //Get the taskTitle from the record
                $hours = $record['end']->format('H') - $record['start']->format('H'); //Get the hours from the record
                $catIndex = 0; //Set the category index
                $taskIndex = 0; //Set the task index
                $addHours = 0; //Need this if task already exists in record to accumulate
                //Loop through each result (one result for each category)
                foreach($result as $r)
                {
                    //If the result is the same category we as the record...
                    if($r['category'] == $catName)
                    {
                        $catFound = true; //Flag the category as found
                        //Loop through the data array for the category
                        foreach($r['data'] as $d)
                        {
                            //If the task is already present...
                            if(isset($d['task']) && $d['task'] == $taskTitle)
                            {
                                $taskExists = true; //Flag the task as found
                                $addHours = $d['hours']; //Get the hours from the existing task to sum up later
                                break; //Break out of the loop
                            }
                            //If the task isn't present, increment the index
                            $taskIndex++;
                        }
                    }
                    //If the category doesn't match the current
                    if(!$catFound)
                    {   //Increment the category index
                        $catIndex++;
                    }
                    else
                    {   //Or break out of the loop
                        break;
                    }
                }
                //Sum the record's hours to totalHours
                $totalHours += $hours;
                //If the task isn't found
                if(!$taskExists)
                {   //Append to the category's data array with the task and hours
                    $result[$catIndex]['data'][] = ['task'=>$taskTitle,'hours'=>$hours];
                    //Increment the count for the number of tasks in the data array
                    $result[$catIndex]['count'] += 1;
                }
                else
                {
                    //Otherwise append to the current data index, and sum the hours
                    $result[$catIndex]['data'][$taskIndex] = ['task'=>$taskTitle, 'hours'=>$addHours+$hours];
                }
            }
        }

        //Set the person's scheduled hours to the second index of the results
        array_unshift($result, ReportHelpers::getEstimatedLongTermTotalScheduledHours($start,$end,$scheduled));
        //Set the total hours to the first index of the results
        array_unshift($result,$totalHours);
        return $result;
    }
}