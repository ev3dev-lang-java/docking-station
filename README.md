# Docking-station

## Vision

- Why do you `Turn off` the Mindstorms Brick?
- Why do you allow to `'live'` your Robot?

## Introduction

[Lego Mindstorms](https://www.lego.com/en-us/mindstorms/) is an educational line of products to learn Robotics.
In the Third generation of the product, exist a [Lego Mindstorms EV3 educational Set](https://education.lego.com/en-us/products/lego-mindstorms-education-ev3-core-set-/5003400)
which includes a [Rechargeable battery (45501)](https://shop.lego.com/en-US/EV3-Rechargeable-DC-Battery-45501) and 
a [Battery Charger (45517)](https://shop.lego.com/en-US/Transformer-10V-DC-45517) but the charger, has been 
designed to be operated by humans.

> Why not help your robot to recharge itself?

## The solution

### 1. Custom connector

Lego Mindstorms has a [Battery Charger (45517)](https://shop.lego.com/en-US/Transformer-10V-DC-45517) 
which allows you to recharge the [Rechargeable battery (45501)](https://shop.lego.com/en-US/EV3-Rechargeable-DC-Battery-45501). 
Doing some changes, it is possible to create a custom connector like this one:

![](./docs/images/connector.jpg)

### 2. Where is the Docking Station?

Once, the robot has a connector to use in an autonomous way, 
it is necessary to help the robot to locate the docking station. 
This kind of problem could be sorted in the Local Navigation category.

> **Local navigation:** The ability to determine one's position relative to objects (stationary or moving) in the environment, and not collide with them as one moves.

### 2.1 Using a Custom LEGO EV3 IR Beacon

Lego Mindstorms EV3 has a IR Sensor which is able to process Signals from a LEGO IR Beacon:

![](./docs/images/irBeacon.jpg)



## Links of reference

- [https://www.doc.ic.ac.uk/~nd/surprise_97/journal/vol4/jmd/](https://www.doc.ic.ac.uk/~nd/surprise_97/journal/vol4/jmd/)
- [http://philohome.com/pf/LEGO_Power_Functions_RC_v120.pdf](http://philohome.com/pf/LEGO_Power_Functions_RC_v120.pdf)
- https://www.youtube.com/watch?v=Ch4NUazpjJ8

