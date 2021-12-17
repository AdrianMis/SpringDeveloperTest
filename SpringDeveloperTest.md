

#SpringDeveloperTest

For this test you will be given a number of events for a figurative insurance system. Your

task is to go over all events and generate a report. The choice of programming language and

the output format is up to you. During the interview, you will present your solution to us and

we can hopefully have some good discussions.

The events can be used to create, terminate and change the premium of the insurance.

The report should include the metrics defined below for every month *between* jan-dec 2020.

**Metrics:**

*Number of contrafts*: The number of contracts that started but not yet been terminated.

*Expected gross written premium (EGWP)*: The expected sum of all premiums for the year.

*Actual gross written premium (AGWP)*: The accumulated premium that should have been

paid in every month.

**Event structure:**

ContractCreatedEvent

{

"contractId": "contractId",

"premium": 100,

"startDate": "2020-02-17"

}

PriceIncreasedEvent

{

"contractId": "contractId",

"premiumIncrease": 10,

"atDate": "2020-02-17"

}

PriceDecreasedEvent

{

"contractId": "contractId",

"premiumReduction": 10,

"atDate": "2020-02-17"

}

ContractTerminatedEvent

{

"contractId": "contractId",

"terminationDate": "2020-02-17"

}





Task 1

Calculate the report using the events: ContractCreatedEvent​and

ContractTerminatedEvent

Example:

Input

ContractCreatedEvent

ContractCreatedEvent

ConctractTerminatedEvent

ConctractTerminatedEvent

1,

2,

1,

2,

100,

100,

2020-01-01

2020-02-01

2020-03-30

2020-04-31

Output

Jan

1

Feb

Mar

Apr

1

May

0

Number of

Contracts

2

2

AGWP

EGWP

100

300

500

600

600

600

600

1200

2400

1500

Task 2

Calculate the report using **all** events:

Example:

Input

ContractCreatedEvent

PriceIncreadEvent

PriceDecreasedEvent

ConctractTerminatedEvent

1,

1,

1,

1,

100,

100,

100,

2020-01-01

2020-02-01

2020-03-01

2020-04-30

Output

Jan

1

Feb

1

Mar

Apr

1

May

0

Number of

Contracts

1

AGWP

EGWP

100

300

400

500

500

500

1200

2300

1300

1300





