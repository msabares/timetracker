<?php


namespace App\Controller;


use App\Repository\CategoryRepository;
use App\Repository\TaskRepository;
use App\Repository\TimesheetRecordRepository;
use App\Services\DetailReport1;
use App\Services\DetailReport2;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;
use DateTime;

class ReportController extends AbstractController
{
    /**
     * return the report based on date range and task categories
     * @Route("/api/detail_report_1", name="get_report", methods={"GET"})
     * @param Request $request
     * @param TimesheetRecordRepository $timesheetRecordRepository
     * @param DetailReport1 $report
     * @return Response
     * @throws \Exception
     */
    public function getDetailReport1(Request $request, TimesheetRecordRepository $timesheetRecordRepository, DetailReport1 $report):Response
    {
        //validate the request parameters. if invalid parameter existing, return bad request header and the error message
        if(count($this->validateParams($request))!=0){
            return $this->json($this->validateParams($request),Response::HTTP_BAD_REQUEST);
        }
        else
        {
            //format the start date and the end date
            $start=(new \DateTime($request->query->get('start')))->format("Y-m-d");
            $end=(new \DateTime($request->query->get('end')))->format("Y-m-d");
            //verify the token
            if (null === $token = $this->container->get('security.token_storage')->getToken()) {
                return $this->json("Invalid Login", Response::HTTP_FORBIDDEN);
            }
            //if the token is not user's token, return error
            if (!is_object($user = $token->getUser())) {
                // e.g. anonymous authentication
                return $this->json("Invalid Login", Response::HTTP_FORBIDDEN);
            }
            //get the person id from the user
            $personID=$user->getPersonID();
            //get the scheduled hours from the user
            $schHours=$user->getScheduledHours();
            //get the hours array from timesheetRecord repository getReport() method
            $hours=$timesheetRecordRepository->selectDetailReport1($start,$end,$personID);
            //get report from ReportOnTaskCategory Service
            $response=$report->buildDetailReport1($start,$end,$hours,$schHours);
            return $this->json($response);
        }
    }

    /**
     * @Route ("/api/detail_report_2", name="detail_report_2", methods={"GET"})
     * @param Request $request
     * @param TimesheetRecordRepository $trRepo
     * @param CategoryRepository $catRepo
     * @param DetailReport2 $report
     * @return Response
     * @throws \Exception
     */
    public function getDetailReport2(Request $request, TimesheetRecordRepository $trRepo, CategoryRepository $catRepo, DetailReport2 $report):Response
    {
        //validate the request parameters. if invalid parameter existing, return bad request header and the error message
        if(count($this->validateParams($request))!=0){
            return $this->json($this->validateParams($request),Response::HTTP_BAD_REQUEST);
        }
        else
        {
            //format the start date and the end date
            $start=(new \DateTime($request->query->get('start')))->format("Y-m-d");
            $end=(new \DateTime($request->query->get('end')))->format("Y-m-d");
            //verify the token
            if (null === $token = $this->container->get('security.token_storage')->getToken()) {
                return $this->json("Invalid Login", Response::HTTP_FORBIDDEN);
            }
            //if the token is not user's token, return error
            if (!is_object($user = $token->getUser())) {
                // e.g. anonymous authentication
                return $this->json("Invalid Login", Response::HTTP_FORBIDDEN);
            }
            //get the person id from the user
            $personID=$user->getPersonID();
            //get the scheduled hours from the user
            $scheduled=$user->getScheduledHours();
            $cats = $catRepo->getTopLevelTaskCategories();
            //get the hours array from timesheetRecord repository getReport() method
            $data=$trRepo->selectDetailReport2($start,$end,$personID);
            //get report from ReportOnTaskCategory Service
            $response=$report->buildDetailReport2($start,$end,$data,$cats,$scheduled);
            return $this->json($response);
        }
    }

    /**
     * validate the parameters of the request
     * @param Request $request
     * @return array
     * @throws \Exception
     */
    public function validateParams(Request $request)
    {
        $result=[];
        $start=$request->query->get('start');
        $end=$request->query->get('end');
        if($start==null){
            $result['message']="The start date can't be empty";
        }
        if($end==null){
            $result['message']="The end date can't be empty";
        }
        if($start==null&&$end==null){
            $result['message']="Invalid Date Range";
        }
        if(new \DateTime($start)>new \DateTime($end))
        {
            $result['message']="The end date should be earlier than start date";
        }
        return $result;
    }


}