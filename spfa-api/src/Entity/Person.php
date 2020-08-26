<?php

namespace App\Entity;

use ApiPlatform\Core\Annotation\ApiSubresource;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Security\Core\User\UserInterface;
use ApiPlatform\Core\Annotation\ApiResource;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use phpDocumentor\Reflection\Types\Null_;
use Symfony\Component\Serializer\Annotation\SerializedName;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use ApiPlatform\Core\Bridge\Doctrine\Orm\Filter\SearchFilter;
use ApiPlatform\Core\Bridge\Doctrine\Orm\Filter\NumericFilter;
use ApiPlatform\Core\Annotation\ApiFilter;
use App\Validator\Constraints\PersonCategoryType;


/**
 *  @ApiResource(
 *     attributes={"security"="is_granted('ROLE_USER')", "pagination_enabled"=true},
 *     itemOperations={
 *          "get" = {
 *           "security"="is_granted('ROLE_ADMIN') or object == user",
 *           "security_message"="You are not authorized to get person",
 *          },
 *      },
 *     collectionOperations={
 *          "get" = {
 *              "security"="is_granted('ROLE_ADMIN')",
 *              "security_message"="You are not authorized to get person",
 *           },
 *          "post" = {
 *              "security" = "is_granted('ROLE_ADMIN')",
 *              "security_message"="You are not authorized to create person",
 *              "validation_groups"={"Default", "create"}
 *          }
 *     },
 *     normalizationContext={"groups"={"person:read"},"swagger_definition_name"="Read"},
 *     denormalizationContext={"groups"={"person:write"},"swagger_definition_name"="Read"}
 *     )
 * @ApiFilter(SearchFilter::class, properties={"email": "exact"})
 * @ORM\Entity(repositoryClass="App\Repository\PersonRepository")
 * @UniqueEntity("email")
 */
class Person implements UserInterface
{
    /**
     * @Groups({"person:read", "person:write", "timesheet_record:read"})
     * @ORM\Id()
     * @ORM\GeneratedValue()
     * @ORM\Column(type="integer", unique=true)
     * @ApiFilter(NumericFilter::class, properties={"personID"})
     */
    private $personID;

    /**
     * @Groups({"person:read","person:write"})
     * @ORM\Column(type="string", length=100, name="firstName")
     *@Assert\Regex(
     *     pattern = "/^('?-?[a-zA-z]{1,}'?-? ?){1,3}$/",
     *     match=true,
     *     message="Your first name may not contain special characters and must be between 1-100 characters"
     * )
     * @Assert\NotBlank(
     *      message = "Your first name may not contain special characters and must be between 1-100 characters"
     * )
     * * @Assert\Length(
     *     max = 100,
     *      maxMessage = "Your first name may not contain special characters and must be between 1-100 characters"
     *     )
     */
    private $firstName;

    /**
     * @ORM\Column(type="string", length=100, name="lastName" )
     *@Groups({"person:read","person:write"})
     *@Assert\Regex(
     *     pattern = "/^('?-?[a-zA-z]{1,}'?-? ?){1,3}$/",
     *     match=true,
     *     message ="Your last name may not contain special characters and must be between 1-100 characters"
     * )
     * @Assert\NotBlank(
     *      message = "Your last name may not contain special characters and must be between 1-100 characters"
     * )
     * * @Assert\Length(
     *     max = 100,
     *      maxMessage = "Your last name may not contain special characters and must be between 1-100 characters"
     *     )
     * This validates name length with no special characters allowed. length:1-100
     */
    private $lastName;

    /**
     * @Groups({"person:read","person:write"})
     * @ORM\Column(type="string", unique=true, name="email", length=255)
     *  @Assert\Email(
     *     message="That email address is not a valid email address.",
     *     checkMX=true
     * )
     * @Assert\NotBlank(
     *      message = "Your Email is too short"
     * )
     * @Assert\Length(
     *     max = 255,
     *      maxMessage = "That email address is not a valid email address."
     *     )
     *
     * this validates email format, and length(255).
     */
    private $email;

    /**
     * @Groups({"person:read","person:write"})
     * @ORM\Column(type="json")
     */
    private $roles = [];

    /**
     *@Groups({"person:read","person:write"})
     * @var string The hashed password
     * @ORM\Column(type="string")
     */
    private $password;

    /**
     *@Groups({"person:read","person:write"})
     * @SerializedName("password")
     * @Assert\NotBlank(
     *     groups={"create"},
     *     message="Password is required"
     *     )
     * @Assert\Regex(
     *     groups={"create"},
     *     pattern="/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[#\\~';$^+=!*<>()@%&]).{8,}$/",
     *     message="Invalid Password"
     * )
     * //https://www.thepolyglotdeveloper.com/2015/05/use-regex-to-test-password-strength-in-javascript/
     */
    private $plainPassword;

    /**
     * @Groups({"person:read","person:write"})
     * @PersonCategoryType
     * @ORM\ManyToOne(targetEntity="App\Entity\Category")
     * @ORM\JoinColumn(name="category_id", referencedColumnName="category_id")
     */
    private $category;

    /**
     *@Groups({"person:read","person:write"})
     * @ORM\Column(type="string")
     * @Assert\Choice(choices={"full time", "part time", "casual", "on leave"}, message="Invalid Status")
     */
    private $employmentStatus;

    /**
     *@Groups({"person:read","person:write"})
     * @ORM\Column(type="float")
     * @Assert\Range(
     *     min = 0.0,
     *     max = 37.5,
     *     minMessage = "This value should be between 0 and 37.5.",
     *     maxMessage = "This value should be between 0 and 37.5."
     * )
     */
    private $scheduledHours;

    public function __construct() {

        //Set default value during construction
        $this->scheduledHours = 0;
    }

    public function getPersonID(): ?int
    {
        return $this->personID;
    }

    public function setPersonID(int $personID): self
    {
        $this->personID = $personID;

        return $this;
    }

    public function getFirstName(): ?string
    {
        return $this->firstName;
    }

    public function setFirstName(string $firstName): self
    {
        $this->firstName = $firstName;

        return $this;
    }

    public function getLastName(): ?string
    {
        return $this->lastName;
    }

    public function setLastName(string $lastName): self
    {
        $this->lastName = $lastName;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function getCategory(): ?Category
    {
        return $this->category;
    }

    public function setCategory(?Category $cat): self
    {
        $this->category = $cat;

        return $this;
    }

    /**
     * A visual identifier that represents this user.
     *@Groups({"person:read"})
     * @see UserInterface
     */
    public function getUsername(): string {
        return (string) $this->email;
    }

    /**
     * @see UserInterface
     */
    public function getRoles(): ?array
    {
        $roles = $this->roles;
        // guarantee every user at least has ROLE_USER
        $roles[] = 'ROLE_USER';

        return array_unique($roles);
    }

    public function setRoles(array $roles): self
    {
        $this->roles = $roles;

        return $this;
    }

    /**
     * @see UserInterface
     */
    public function getPassword(): string
    {
        return (string) $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

        return $this;
    }

    public function getPlainPassword(): ?string
    {
        return $this->plainPassword;
    }

    public function setPlainPassword($plainPassword): self
    {
        $this->plainPassword = trim($plainPassword);
        return $this;
    }

    /**
     * @Groups({"person:read"})
     * @see UserInterface
     */
    public function getSalt()
    {
        // not needed when using the "bcrypt" algorithm in security.yaml
    }

    /**
     * @see UserInterface
     */
    public function eraseCredentials()
    {
        // If you store any temporary, sensitive data on the user, clear it here
        $this->plainPassword = null;
    }

    public function getEmploymentStatus(): ?string
    {
        return $this->employmentStatus;
    }

    public function setEmploymentStatus(string $employmentStatus): self
    {
        $this->employmentStatus = $employmentStatus;

        return $this;
    }

    public function getScheduledHours(): ?float
    {
        return $this->scheduledHours;
    }

    public function setScheduledHours(float $scheduledHours): self
    {
        $this->scheduledHours = $scheduledHours;

        return $this;
    }

}
