Feature: Search Functionality feature tests

  @desktopBrowser @androidBrowser
  #Static test that does not rely on test data in external files.
  #Assuming feature requirement of search bar functionality behaving by only displaying
  #products that contain the input provided in the search bar input field.
  Scenario Outline: User can search for products only containing searched for text
    When User searches for products containing "<searchInput>"
    Then User only sees products containing "<searchInput>" in search page
    And User sees total of <numOfProducts> in search page

    @dev
    Examples:
    | searchInput | numOfProducts |
    | shirt       | 1             |
    | blouse      | 1             |
    #Should fail by displaying shirt and blouse next to dresses and actual total being 7.
    | dress       | 5             |