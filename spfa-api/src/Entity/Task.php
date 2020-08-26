<?php

namespace App\Entity;

use ApiPlatform\Core\Annotation\ApiResource;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\ORM\Mapping\ManyToOne;
use Doctrine\ORM\Mapping\JoinColumn;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
use ApiPlatform\Core\Bridge\Elasticsearch\DataProvider\Filter\MatchFilter;
use App\Validator\Constraints\TaskCategoryType;

/**
 * @ApiResource(
 *     attributes={"security"="is_granted('ROLE_USER')", "pagination_enabled"=false},
 *     itemOperations={
 *          "get" = {
 *            "security"="is_granted('ROLE_USER')",
 *            "security_message"="You are not authorized to get task",
 *          },
 *     },
 *     collectionOperations={
 *          "get" = {
 *            "security"="is_granted('ROLE_USER')",
 *            "security_message"="You are not authorized to get task",
 *          },
 *          "post" = {
 *              "security" = "is_granted('ROLE_ADMIN')",
 *              "security_message"="You are not authorized to create task",
 *     },
 *     },
 *     normalizationContext={"groups"={"task:read"},"swagger_definition_name"="Read"},
 *     denormalizationContext={"groups"={"task:write"},"swagger_definition_name"="Read"},
 * )
 * @ORM\Entity(repositoryClass="App\Repository\TaskRepository")
 */
class Task
{
    /**
     * @Groups({"task:read", "task:write", "timesheet_record:read"})
     * @ORM\Id()
     * @ORM\GeneratedValue()
     * @ORM\Column(type="integer")
     */
    private $taskID;

    /**
     * @Groups({"task:read", "task:write", "timesheet_record:read"})
     *
     * @ORM\Column(type="string", length=255)
     *
     * @Assert\Length(
     *      max = 255,
     *      maxMessage = "Task Title must be no more than 255 characters"
     * )
     *
     * @Assert\Regex(
     *     pattern = "/^[A-Za-z0-9 ()\-_.,!:'\/$]*$/",
     *     match=true,
     *     message="Task Title can only contain alphanumeric or punctuation characters"
     * )
     * @Assert\NotBlank(
     *      message = "Task Title must be at least one character"
     * )
     *
     */
    private $title;

    /**
     * @Groups({"task:read", "task:write", "timesheet_record:read"})
     *
     * @ORM\Column(type="float")
     *
     * @Assert\Range(
     *      min= 0.0,
     *      max= 1000000,
     *
     *     minMessage ="Hours Estimate must be at least 0",
     *     maxMessage="Hours Estimate cannot be greater than 1,000,000"
     *     )
     */
    private $hoursEstimate;

    /**
     * @Groups({"task:read", "task:write"})
     * @TaskCategoryType
     * @ORM\ManyToOne(targetEntity="App\Entity\Category")
     * @ORM\JoinColumn(name="category_id", referencedColumnName="category_id", nullable=false)
     */
    private $category;

    /**
     * @ORM\Column(type="float")
     */
    private $percentComplete;

    public function __construct()
    {
        //Set default value during construction
        $this->hoursEstimate = 0;
        $this->percentComplete = 0;
    }

    public function getTaskID(): ?int
    {
        return $this->taskID;
    }

    public function getTitle(): ?string
    {
        return $this->title;
    }

    public function setTitle(string $title): self
    {
        $this->title = trim($title);

        return $this;
    }

    public function getHoursEstimate(): ?float
    {
        return $this->hoursEstimate;
    }

    public function setHoursEstimate(float $hoursEstimate): self
    {
        $this->hoursEstimate = $hoursEstimate;

        return $this;
    }

    public function getCategory(): ?Category
    {
        return $this->category;
    }

    public function setCategory(?Category $category): self
    {
        $this->category = $category;

        return $this;
    }

    public function getPercentComplete(): ?float
    {
        return $this->percentComplete;
    }

    public function setPercentComplete(float $percentComplete): self
    {
        $this->percentComplete = $percentComplete;
        return $this;
    }
}

