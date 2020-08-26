<?php

namespace App\Entity;

use ApiPlatform\Core\Annotation\ApiResource;
use ApiPlatform\Core\Annotation\ApiFilter;
use ApiPlatform\Core\Bridge\Doctrine\Orm\Filter\SearchFilter;
use ApiPlatform\Core\Bridge\Doctrine\Orm\Filter\OrderFilter;
use Doctrine\ORM\Mapping as ORM;
use Doctrine\ORM\Mapping\ManyToOne;
use Doctrine\ORM\Mapping\JoinColumn;
use Symfony\Component\Validator\Constraints as Assert;
use App\Validator\Constraints as CustomAssert;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * @ApiResource(attributes={"security"="is_granted('ROLE_USER')", "pagination_enabled"=false},
 *          normalizationContext={"groups"={"category:read"},"swagger_definition_name"="Read"},
 *     denormalizationContext={"groups"={"category:write"},"swagger_definition_name"="Write"},
 *     itemOperations={
 *          "get" = {
 *            "security"="is_granted('ROLE_USER')",
 *            "security_message"="You are not authorized to get category",
 *          },
 *     },
 *     collectionOperations={
 *          "get" = {
 *            "security"="is_granted('ROLE_USER')",
 *            "security_message"="You are not authorized to get category",
 *          },
 *          "post" = {
 *              "security" = "is_granted('ROLE_ADMIN')",
 *              "security_message"="You are not authorized to create category",
 *          },
 *     }),
 *
 * @CustomAssert\ParentCategoryType
 * @ApiFilter(SearchFilter::class, properties={"categoryName": "exact", "categoryID": "exact", "categoryType": "exact", "parentCategory.categoryName": "exact"})
 * @ApiFilter(OrderFilter::class, properties={"categoryName"})
 * @ORM\Entity(repositoryClass="App\Repository\CategoryRepository")
 */
class Category
{
    /**
     * @Groups({"category:read", "category:write"})
     * @ORM\Id()
     * @ORM\GeneratedValue()
     * @ORM\Column(type="integer")
     *
     * @Assert\Type(
     *     type="integer",
     *     message="This value {{ value }} is not a valid {{ type }}.",
     *     groups={"get"}
     * )
     *
     * @Assert\Blank(
     *     message="Category ID must be empty.",
     *     groups={"add"}
     * )
     *
     */
    private $categoryID;

    /**
     * @Groups({"category:read", "category:write"})
     * @ManyToOne(targetEntity="Category", cascade={"persist"})
     * @JoinColumn(name="parent_id", referencedColumnName="category_id")
     *
     */
    private $parentCategory;

    /**
     * @Groups({"category:read", "category:write","person:read"})
     * @ORM\Column(type="string", length=255, nullable=false)
     * @Assert\Length(
     *     min=2,
     *     max=100,
     *     minMessage = "Category Name must be at least two characters.",
     *     maxMessage = "Category Name can't be greater than 100 characters."
     *     )
     * @Assert\Regex(
     *     pattern = "/^[a-zA-Z]+[0-9a-zA-Z \,\&\-\(\)\/]*$/",
     *     message = "Category Name must start with a letter, and only contain letters, numbers, spaces, hyphens, forward slashes, parentheses, commas and ampersands."
     *     )
     * @Assert\NotBlank(
     *     message = "Category Name must not be empty."
     *     )
     */
    private $categoryName;

    /**
     * @Groups({"category:read", "category:write"})
     * @ORM\Column(type="string", length=255, nullable=true)
     *
     * @Assert\Length(
     *     max=255,
     *     maxMessage = "The category description cannot be greater than 255 characters."
     *     )
     */
    private $categoryDescription;

    /**
     * @Groups({"category:read", "category:write"})
     * @ORM\Column(type="integer", nullable=true)
     */
    private $categoryType;


    public function setCategoryID(int $categoryID): self
    {
        $this->categoryID = $categoryID;

        return $this;
    }

    public function getCategoryID(): ?int
    {
        return $this->categoryID;
    }

    public function getParentCategory(): ?self
    {
        return $this->parentCategory;
    }

    public function setParentCategory(?self $parentCategory): self
    {
        $this->parentCategory = $parentCategory;

        return $this;
    }

    public function getCategoryName(): ?string
    {
        return $this->categoryName;
    }

    public function setCategoryName(string $categoryName): self
    {
        $this->categoryName = trim($categoryName);

        return $this;
    }

    public function getCategoryDescription(): ?string
    {
        return $this->categoryDescription;
    }

    public function setCategoryDescription(?string $categoryDescription): self
    {
        $this->categoryDescription = $categoryDescription;

        return $this;
    }

    public function getCategoryType(): ?int
    {
        return $this->categoryType;
    }

    public function setCategoryType(int $categoryType): self
    {
        $this->categoryType = $categoryType;

        return $this;
    }


}
