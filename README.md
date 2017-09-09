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
If you do some changes in the cable of the charger, it is possible to create a custom connector like this one:

![](./docs/images/connector.jpg)

### 2. Is charging the Brick?

#### 2.1 Using a EV3 Light Sensor

The EV3 Rechargeable battery has 2 leds (green and red). 
Using lego Mindstorms has a [Light Sensor (45506)](https://shop.lego.com/en-US/EV3-Color-Sensor-45506) 
which it could be used to read the green led enabled which indicates that the battery is charging. 

![](./docs/images/lightSensorReadingEV3Battery.jpg)

**Limitations:** If you connect the sensor closed to the green led, you loose a Sensor Port.

#### 2.2 Using a HS110 Smart Plug 

In the market exist Smart Plugs which are possible to operate using a REST API.
One product used in this project is [TP HS 110](http://www.tp-link.com/us/products/details/cat-5516_HS110.html):

![](./docs/images/HS110.jpg)

Using this kind of devices, is possible to know if the EV3 Brick are charging or not:

**Output when an EV3 Brick is not connected:**

```
Parsing: {"emeter":{"get_realtime":{"current":0.012227,"voltage":242.435362,"power":0,"total":0.006000,"err_code":0}}}
Power: 0.0W, Total Consumption: 0.006kWh Current: 0.012227A Voltage: 242.435362
```

**Output when an EV3 Brick is connected:**

```
Parsing: {"emeter":{"get_realtime":{"current":0.038435,"voltage":242.617563,"power":4.639256,"total":0.006000,"err_code":0}}}
Power: 4.639256W, Total Consumption: 0.006kWh Current: 0.038435A Voltage: 242.617563
```

### 3. Where is the Docking Station?

Once, the robot has a connector to use in an autonomous way, 
it is necessary to help the robot to locate the docking station. 
This kind of problem could be sorted in the **Local Navigation** category.

> **Local navigation:** The ability to determine one's position relative to objects (stationary or moving) in the environment, and not collide with them as one moves.

### 3.1 Using a Custom LEGO EV3 IR Beacon

Lego Mindstorms EV3 has a [IR Sensor (45509)](https://shop.lego.com/en-US/EV3-Infrared-Sensor-45509) 
which is able to process signals from a [IR Beacon (45508)](https://shop.lego.com/en-US/EV3-Infrared-Beacon-45508):

![](./docs/images/irBeacon.jpg)



## Links of reference

- http://www.plastibots.com/index.php/2010/11/13/pulito-featuring-dflex/
- [https://www.doc.ic.ac.uk/~nd/surprise_97/journal/vol4/jmd/](https://www.doc.ic.ac.uk/~nd/surprise_97/journal/vol4/jmd/)
- [http://philohome.com/pf/LEGO_Power_Functions_RC_v120.pdf](http://philohome.com/pf/LEGO_Power_Functions_RC_v120.pdf)
- https://www.youtube.com/watch?v=Ch4NUazpjJ8

