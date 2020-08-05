@dev @prod
Feature: Homepage functionality
  scenarios for asserting correct homepage functionality

  Background:
    Given User is on the homepage

  @desktopBrowser
  Scenario: User can view popular and best seller items
    Then User sees all popular items with expected content and expected images
    When User clicks best sellers tab
    Then User sees all best seller items with expected content and expected images
    #Check again to confirm that switching back to Popular loads items to display correctly.
    When User clicks popular tab
    Then User sees all popular items with expected content and expected images

  @desktopBrowser @androidApp
  Scenario Outline: User sees expected ad image and is redirected to expected link when clicking top ads
    Then User sees <numOfAd> top ad loaded with correct image
    When User clicks on top <numOfAd> ad
    Then User is redirected to correct link for top <numOfAd> ad

    Examples:
      | numOfAd |
      | 0       |
      | 1       |

  @desktopBrowser
  Scenario Outline: User is redirected to expected link when clicking bottom ads
    Then User sees <numOfAd> bottom ad loaded with correct image
    When User clicks on bottom <numOfAd> ad
    Then User is redirected to correct link for bottom <numOfAd> ad

  Examples:
    | numOfAd |
    | 0       |
    | 1       |
    | 2       |
    | 3       |
    | 4       |

  @desktopBrowser
  Scenario: User can view product details when clicking on "Quick View" on product image
    When User moves mouse over a random popular product and clicks the quick view button
    Then User sees the quick view open for a product
    And User sees correct price, title, description for current product in Quick View
    And User sees all the product images displayed full size when moving mouse over each thumbnail
    When User picks a random color of the product in quick view
    Then User sees only images for the selected color in quick view