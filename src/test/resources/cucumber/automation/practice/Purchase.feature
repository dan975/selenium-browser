Feature: Purchase tests

  @dev @prod @desktopBrowser
  Scenario: User can add to cart shirts, summer dresses and popular items and view them in checkout
    Given User is signed in with main account
    And User is on the homepage
    When User clicks t shirts under women tab in header
    And User adds random product to cart in "tshirt" sub category page
    Then User sees addition confirmation popup with all item quantity and sum totals
    When User clicks continue shopping button in addition confirmation popup
    And User clicks summer dresses under women tab in header
    And User opens quick view for random product in "summerDresses" sub category page
    Then User sees the quick view open for a product
    When User adds random quantity in range 1 to 3 of item to cart
    Then User sees addition confirmation popup with all item quantity and sum totals
    When User clicks continue shopping button in addition confirmation popup
    And User clicks website logo
    And User adds random popular product to cart
    Then User sees addition confirmation popup with all item quantity and sum totals
    When User clicks proceed to checkout button in addition confirmation popup
    Then User sees all expected cart items in summary page with expected cost totals

  @dev @desktopBrowser
  Scenario: User can purchase best seller items
    Given User is signed in with main account
    And User is on the homepage
    When User adds popular product with index 1 to cart
    Then User sees addition confirmation popup with item quantity 1 and total price "$27.00"
    When User clicks continue shopping button in addition confirmation popup
    And User adds popular product with index 3 to cart
    Then User sees addition confirmation popup with item quantity 2 and total price "$77.99"
    When User clicks proceed to checkout button in addition confirmation popup
    Then User sees following products in checkout in summary page
      | productTitle  | quantity | total   |
      | Blouse        | 1        |  $27.00 |
      | Printed Dress | 1        |  $50.99 |
    And User sees purchase totals in summary page
      | productTotal | shipping | totalWithoutTax | tax   | total   |
      | $77.99       | $2.00    | $79.99          | $3.20 |  $83.19 |
    When User clicks proceed to checkout button in checkout
    Then User sees correct address details for main address in checkout address page
    When User clicks proceed to checkout button in checkout
    And User checks Terms of Service checkbox
    And User clicks proceed to checkout button in checkout
    Then User sees following products in checkout in payment page
      | productTitle  | quantity | total   |
      | Blouse        | 1        |  $27.00 |
      | Printed Dress | 1        |  $50.99 |
    #Absence of total without tax could be considered a defect
    And User sees purchase totals in payment page
      | productTotal | shipping | tax   | total   |
      | $77.99       | $2.00    | $3.20 |  $83.19 |
    When User chooses bank wire payment method
    Then User sees the bank wire order summary with total cost of purchase "$83.19"
    When User clicks button to confirm order
    And User clicks button to go to order history page after purchase
    #Invoice download link may not be present when executing in Chrome
    Then User downloads latest invoice pdf and sees purchase total "$83.19"

  @dev @prod @desktopBrowser
  Scenario: User can view older invoices
    Given User is signed in with invoice account
    When User goes to Order history page
    Given User has at least one older purchase in his order history
    #Invoice download link may not be present when executing in Chrome
    Then User downloads latest invoice pdf and sees purchase total "$61.88"
