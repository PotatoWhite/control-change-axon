# Chapter 2: Transaction (Monolithic 과 Microservice 의 변경관리 차이)

## Transaction 

 도메인객체가 갖는 속성(Property)의 변경은 프로세스를 통해 이루어진다. 프로세스가 수행되는 동안 항상 일관 되게 유지되어야 하고, 다른 프로세스에 의해 영향 받지 않아야 한다.
이런 변경 단위를 Transaction 이라고 한다.

## Monolithic 과 Microservice 의 Transaction 차이

### Monolithic Transaction

 Monolithic 하나의 시스템에 여러 종류의 도메인 객체가 존재한다. 이를 하나의 프로세스에서 처리하기 위해 RDMS의 Locking 기능을 사용하여, 도메인 객체의 변경을 보장하는 것이 일반적이고 효율적이다. 
Database에서 제공되는 Locking을 통해 Concurrency 문제를 해결할 수 있으며, 이를 통해 Transaction의 ACID를 보장 받을 수 있다.

즉, 프로세스가 수행되는 동안 RDBMS에 신뢰성을 통해 수행되며, 수행 완료시 즉시 Consistency를 보장 받아 왔다.
이러한 신뢰성을 위해 보장되어야 할 항목들을 전통적으로 ACID라고 한다.

    - Atomicity : 트랜잭션의 모든 연산이 성공적으로 완료되거나, 아니면 전혀 실행되지 않은 상태를 보장한다.
    - Consistency : 트랜잭션이 성공적으로 완료되면, 데이터베이스는 일관성 있는 상태로 유지된다.
    - Isolation : 트랜잭션은 동시에 실행되는 다른 트랜잭션의 연산에 영향을 받지 않는다.
    - Durability : 트랜잭션이 성공적으로 완료되면, 결과는 영구적으로 반영된다.

### Microservice Transaction

 반면에 Microservice는 도메인객체들을 여러개의 서비스로 분리되어 저장되고 처리되며, 각각의 서비스가 독립적으로 배포되고 운영되곤 한다. Database 또한 Microservice별로 다양한 종류를 사용하며,  한번의 Transaction 처리에 여러 Microservice들이 참여하게 되는 경우, RDBMS의 Locking 등의 기능을 통해 Transaction의 ACID를 보장 받을 수 없다.

 IT 전반적으로 비즈니스환경에 사용자와 트랜잭션이 폭발적으로 증가 하게 되어 Monolithic 시스템을 사용하던 시대와 다르게 Availability와 Scalability를 중요성도 높아졌다. 

 이런 요청에 따라 시스템이 점점 더 정교하게 분산되면 변경된 도메인객체의 변경결과를 타 서비스에 전파해야할 수도 있다.  또한 서비스 환경에서도 Transaction의 종료시 즉시 결과를 보장 받는 것이 아닌, 비동기적으로 Eventual Consistency를 제공하는 UX또한 많은 서비스에서 제공 되고 있다.

분산환경에서는 Availability와 Consistency를 Trade-off 하는 것이 일반적으로, 2000년 Eric Brewer 가 CAP 이론을 제시하였다.

    - Consistency : 모든 노드가 동일한 데이터를 보여준다.
    - Availability : 모든 요청에 대해 응답을 보장한다.
    - Partition tolerance : 네트워크 장애가 발생해도 시스템이 동작한다. (Network 장애 발생시, 일부 노드가 다른 노드와 통신이 불가능한 상태)

 CAP이론을 요약하자면 분산환경에서 네트워크 문제로 두 데이터베이스간의 Partition이 발생했을 때 일관성(Consistency)를 위해 하나를 버릴 것이냐? 아니면 Partition된 상태에서도 서비스 가용성(Availablity)을 보장할 것이냐?의 Trade-off 문제가 있다는 것이다.

 따라서 분산환경에서는 BASE를 보장 해야한다고 한다.

    - Basically Available : 시스템이 정상적으로 동작하는 경우는 보장한다.
    - Soft state : 시스템의 상태는 언제든지 변경될 수 있다.
    - Eventual consistency : 시스템의 상태가 일정 시간이 지나면 일관성이 보장된다.


 이런 분산환경에서는 일관성을 보장하기 위해 Eventual Consistency를 보장해야 한다.  
 Eventual Consistency는 데이터 변경이 발생하면 이를 다른 서비스에 전파하여 일관성을 보장한다.
    
    - Polling : 일정 시간마다 데이터를 조회하여 일관성을 보장한다.
    - Eventual Consistency : 데이터 변경이 발생하면 이를 다른 서비스에 전파하여 일관성을 보장한다.
    - CQRS : 데이터 변경이 발생하면 이를 다른 서비스에 전파하여 일관성을 보장한다.

이 중에 CQRS를 사용하여 일관성을 보장하는 방법을 살펴보자.
