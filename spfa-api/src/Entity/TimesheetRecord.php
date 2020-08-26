<?php

namespace App\Entity;

use ApiPlatform\Core\Annotation\ApiResource;
use ApiPlatform\Core\Annotation\ApiFilter;
use ApiPlatform\Core\Bridge\Doctrine\Orm\Filter\DateFilter;
use Doctrine\ORM\Mapping as ORM;
use phpDocumentor\Reflection\Types\Null_;
use Symfony\Component\Validator\Constraints as Assert;
use DateTime;
use Symfony\Component\Serializer\Annotation\Groups;
use ApiPlatform\Core\Bridge\Doctrine\Orm\Filter\NumericFilter;


/**
 * @ApiResource(
 *     attributes={"security"="is_granted('ROLE_USER')", "pagination_enabled"=false},
 *     collectionOperations={"get", "post"},
 *     itemOperations={
 *          "get"={},
 *          "put"={"security"="object.getPerson() == user"},
 *          "delete"
 *     },
 *     normalizationContext={"groups"={"timesheet_record:read"}, "swagger_definition_name"="Read"},
 *     denormalizationContext={"groups"={"timesheet_record:write"}, "swagger_definition_name"="Write"},
 * )
 *
 * @ApiFilter(DateFilter::class, properties={"start", "end"})
 * @ORM\Entity(repositoryClass="App\Repository\TimesheetRecordRepository")
 * @ORM\Table(name="TimesheetRecord")
 * @ApiFilter(NumericFilter::class, properties={"person.personID"})
 */
class TimesheetRecord
{
    /**
     * @Groups({"timesheet_record:read"})
     * @ORM\Id()
     * @ORM\GeneratedValue()
     * @ORM\Column(type="integer")
     */
    private $recordID;

    /**
     * @Groups({"timesheet_record:read", "timesheet_record:write"})
     * @ORM\Column(type="datetime", name="start_time")
     * @Assert\NotBlank(message="The start time cannot be blank")
     */
    private $start;

    /**
     * @Groups({"timesheet_record:read", "timesheet_record:write"})
     * @ORM\Column(type="datetime", name="end_time")
     * @Assert\Expression(
     *     "this.getStart() < this.getEnd()",
     *      message="The End Time must be after the Start Time"
     * )
     * @Assert\NotBlank(message="The end time cannot be blank")
     */
    private $end;

    /**
     * @Groups({"timesheet_record:read", "timesheet_record:write"})
     * @ORM\Column(type="string", length=255, nullable=false)
     * @Assert\Choice(choices={"on-site", "off-site"}, message="Must choose a valid working site")
     */
    private $siteLocation = "on-site";

    /**
     * @Groups({"timesheet_record:read", "timesheet_record:write"})
     * @ORM\Column(type="string", length=255, nullable=true)
     * @Assert\Length(
     *     max=255, maxMessage="Comment must be less than 255 characters")
     */
    private $comment;

    /**
     * @Groups({"timesheet_record:read", "timesheet_record:write"})
     * @ORM\ManyToOne(targetEntity="App\Entity\Task")
     * @ORM\JoinColumn(name="task_ID", referencedColumnName="task_id",nullable=false)
     */
    private $task;

    /**
     * @Groups({"timesheet_record:read", "timesheet_record:write"})
     * @ORM\ManyToOne(targetEntity="Person")
     * @ORM\JoinColumn(nullable=false, referencedColumnName="person_id")
     */
    private $person;

    public function getStart()
    {
        return $this->start;
    }

    public function setStart($timeIn = null): self
    {
        $this->start = $timeIn; //yyyy-MM-dd h:m:s am

        return $this;
    }

    public function getEnd()
    {
        return $this->end;
    }

    public function setEnd($timeOut = null): self
    {
        $this->end = $timeOut;
        return $this;
    }

    public function getRecordID(): ?int
    {
        return $this->recordID;
    }

    public function getSiteLocation(): ?string
    {
        return $this->siteLocation;
    }

    public function setSiteLocation(?string $siteLocation = null): self
    {
        $this->siteLocation = $siteLocation;

        return $this;
    }

    public function getComment(): ?string
    {
        return $this->comment;
    }

    public function setComment(?string $comment): self
    {
        $this->comment = trim($comment);

        return $this;
    }

    public function getTask(): ?Task
    {
        return $this->task;
    }

    public function setTask(?Task $task): self
    {
        $this->task = $task;

        return $this;
    }

    public function getPerson(): ?Person
    {
        return $this->person;
    }

    public function setPerson(?Person $person): self
    {
        $this->person = $person;

        return $this;
    }


}

