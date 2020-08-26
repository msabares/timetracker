<?php


namespace App\Services;


class ReportHelpers
{
    /**
     * get total scheduled hours between two week interval, or between the last resting date and the end date
     * @param $start
     * @param $end
     * @param $schHours
     * @return float|int
     * @throws \Exception
     */
    public static function getWeeklyTotalScheduledHours($start, $end, $schHours)
    {
        //get the dates diff between start and end date
        $diff=ceil((strtotime($end)-strtotime($start))/ 86400)+1;
        //if it's two weeks, times the scheduled hours by 2
        if($diff==14){
            return $schHours*2;
        }
        //else calculate the hours between restarting date and end date
        else{
            //get scheduled hours per date
            $schHoursPerDay=round($schHours/5.0,2);
            //get the date of last Sunday as the first day in the week of the end day
            $firstDayOfWeek=strtotime('last sunday', strtotime($end));
            //get the date of this Saturday as the last day in the week of restarting day
            $endDayOfWeek=strtotime('this saturday',strtotime($start));
            //if the week of restarting date is same with the week of the end date, return the diff between end date
            //   and restarting date times scheduled hours per day
            $start= date('w', strtotime($start))==0?date('Y-m-d',strtotime($start.' +1 days')):$start;
            $diff=ceil((strtotime($end)-strtotime($start))/ 86400)+1;
            if((new \DateTime($start))->format('W')==(new \DateTime($end))->format('W')){
                return $diff*$schHoursPerDay;
            }
            // else return the diff between end date and the first day in the week of end date,
            //         plus the diff between the restarting date and the last date in the week of restarting date,
            //         then times with scheduled hours per day.
            else{
                return ceil(((strtotime($end)-$firstDayOfWeek)+($endDayOfWeek-strtotime($start)))/86400)*$schHoursPerDay;
            }
        }
    }

    public static function getEstimatedLongTermTotalScheduledHours($start,$end,$wklyScheduled) : int
    {
        $dailyScheduled = $wklyScheduled /7 ;
        $totalDays = (strtotime($end) - strtotime($start)) / 86400 + 1;
        return round($totalDays * $dailyScheduled,0);
    }

}