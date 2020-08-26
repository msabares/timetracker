<?php


namespace App\Services;


use App\Repository\CategoryRepository;
use App\Services\ReportHelpers;

class DetailReport1
{

    private $studentContact=[];
    private $compFunction=[];
    private $otherAssign=[];

    /**
     * DetailReport1 constructor. Get 'Student Contact' category group, 'Complementary Function' category group and 'Other Assignment' category group
     *                 from CategoryRepository and build up the categoryName array for each property
     * @param CategoryRepository $categoryRepository
     * @throws \Doctrine\DBAL\DBALException
     */
    public function __construct(CategoryRepository $categoryRepository)
    {
        foreach ($categoryRepository->getGroupCategories("Student Contact") as $cat){
            $this->studentContact[]=$cat['catName'];
        }
        foreach ($categoryRepository->getGroupCategories("Complementary Function") as $cat){
            $this->compFunction[]=$cat['catName'];
        }
        foreach ($categoryRepository->getGroupCategories("Other Assignments") as $cat){
            $this->otherAssign[]=$cat['catName'];
        }
    }

    /**
     * generate report of hours spent on each root category during each two week interval
     * @param string $start the start date
     * @param string $end the end date
     * @param  $hours  the hours array return from getReport() in timesheetRecord Repository
     * @param $schHours the person's scheduled hours property
     * @return array with 8 fields: 'Timesheet Date' will begin from start date, restart from two weeks(14 days) after, end with the end date;
     *                              'Scheduled paid hours' get the scheduled paid hours between the two weeks period, or between the last restart date and the end date.
     *                              'Actual Student Contact' get the hours spent on  Student Contact task category between the two weeks period, or between the last restart date and the end date.
     *                              'Actual Complementary Function'  get the hours spent onComplementary Function task category
     *                              'Actual Other Assignment' get the hours spent on Other Assignment task category
     *                              'Actual Other Not Listed' get the hours spent on other task categories not listing in the above 3 categories
     *                              'Timesheet Total' get all hours spent on all task categories
     *                              'variant hours' get the difference between 'Timesheet Total' and 'Scheduled hours'
     * @throws \Exception
     */
    public function buildDetailReport1(string $start, string $end, $hours, $schHours): array
    {
        $result=[];
        $row=[];
        //initialize 'Timesheet Date' field as the start date
        $row['Timesheet Date']=$start;
        $totalSContact=0;
        $totalCFunction=0;
        $totalOAssignments=0;
        $totalONListing=0;
        $totalTSHours=0;
        $totalScheduled=0;
        $totalVariant=0;
        //loop through each two week intervals until the end date
        while ($row['Timesheet Date']<=$end){
            //initialize each field
            $row['Actual Student Contact']=0;
            $row['Actual Complementary Function']=0;
            $row['Actual Other Assignment']=0;
            $row['Actual Other Not Listed']=0;
            $row['Timesheet Total']=0;
            //if the dates between the end date and the restarting date longer than or equal to 2 weeks, get one day before the restarting timesheet date as the end of period, otherwise get the end date
            $weekendInterval=((strtotime($end)-strtotime($row['Timesheet Date']))/86400)>=14?date('Y-m-d',strtotime($row['Timesheet Date'].'+13 days')):$end;
            //loop through each row of $hours array
            foreach ($hours as $hour){
                //select the rows in $hours whose 'd' filed is between the 'Timesheet Date' and the end of following two weeks
                if($hour['d']>=$row['Timesheet Date']&&$hour['d']<=$weekendInterval){
                    //if 'cat' field is in studentContact category array
                    if($hour['cat'] == 'Student Contact'){
                        //sum up the hours
                        $row['Actual Student Contact']+=$hour['hours'];
                        $totalSContact+=$hour['hours'];
                    }
                    //if 'cat' field is in complementary function category array
                    elseif ($hour['cat'] == 'Complementary Function'){
                        $row['Actual Complementary Function']+=$hour['hours'];
                        $totalCFunction+=$hour['hours'];
                    }
                    //if 'cat' field is in other assignment category array
                    elseif ($hour['cat'] == 'Other Assignments'){
                        $row['Actual Other Assignment']+=$hour['hours'];
                        $totalOAssignments+=$hour['hours'];
                    }
                    //else calculate hours spent in other categories
                    else{
                        $row['Actual Other Not Listed']+=$hour['hours'];
                        $totalONListing+=$hour['hours'];
                    }
                }
                //sum up the total hours
                $row['Timesheet Total']=$row['Actual Student Contact']+$row['Actual Complementary Function']+$row['Actual Other Assignment']
                    +$row['Actual Other Not Listed'];
            }
            $totalTSHours+=$row['Timesheet Total'];
            //get the scheduled paid hours between weekendInterval
            $row['Scheduled Paid Hours']=ReportHelpers::getWeeklyTotalScheduledHours($row['Timesheet Date'],$weekendInterval,$schHours);
            $row['variant hours']=$row['Timesheet Total']-$row['Scheduled Paid Hours'];
            $totalScheduled+=$row['Scheduled Paid Hours'];
            $result[]=$row;
            $row['Timesheet Date']=date('Y-m-d',strtotime($row['Timesheet Date'].' +14 days'));
            $totalVariant+=$row['variant hours'];
        }
        //Summarize row: sum up all hours in each field
        $result[]=['Timesheet Date'=>'Summary','Scheduled Paid Hours'=>$totalScheduled,'Actual Student Contact'=>$totalSContact,'Actual Complementary Function'=>$totalCFunction,
            'Actual Other Assignment'=>$totalOAssignments,'Actual Other Not Listed'=>$totalONListing,
            'Timesheet Total'=>$totalTSHours,'variant hours'=>$totalVariant];
        return $result;
    }
}