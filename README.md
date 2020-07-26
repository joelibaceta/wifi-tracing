![](doc/images/project_banner.svg)

Inspired by [bluetrace](https://bluetrace.io/)

<br />

This alternative propose to use a mutiple wifi signal strength measurements to triangulate the positions within a physical location.

This demo contains: 

- An android and raspberry aplications as end user applications.
- A website to monitor the people position in a floorplan. (For demo purpose only)
- A backend where historical positioning data is stored

## How it works?

1. Get the distances from the main wifi points. 

![](doc/images/Sample.svg)

2. Use 2d trilateration to get x, y position estimation in the floorplan

  Trilateration allow us to estimate position coordinates using distances from recerence points.

  ![](doc/images/trilateration.jpg)

3. Store position trought the time


## References

- [User Configurable Indoor
Positioning System using WiFi
Trilateration and Fingerprinting](https://www.diva-portal.org/smash/get/diva2:1105921/FULLTEXT02)
- [On Improved Projection Techniques to Support Visual Exploration of Multidimensional DataSets](http://repositorio.icmc.usp.br/bitstream/handle/RIICMC/6868/relatorio_207.pdf?sequence=1http://repositorio.icmc.usp.br/bitstream/handle/RIICMC/6868/relatorio_207.pdf?sequence=1)

