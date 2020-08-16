Feature: Place an Order in Techlistic

Scenario: Automate End to End Buy Order functionality

Given User is on Techlistic Login page
When User clicks on sign in providing Username and Password
Then User is on Homepage
When User clicks on T-Shirts submenu under Womens menu
Then User is on T-Shirts list page
When User clicks on More button on 1st T-Shirt
Then User is on customize product page
When User adds T-Shirt by customizing and clicks on checkout
Then User is on Checkout page
When User Checkout the T-Shirt by confirming 
Then user should see Order Confirmation message