<em>TP 6 - Análisis previo - Grupo 2</em>
<h1>Estudio de una simulación para averiguar la cantidad de desarrolladores y la frecuencia óptima de actualización de prioridades, para un proyecto con tareas de diferente prioridad</h1>

Una empresa en desarrollo de software está en proceso de planificación para un nuevo proyecto de gran envergadura. Con el objetivo de optimizar la eficiencia del equipo asignado a este proyecto, se propone realizar un análisis con datos del avance de otros proyectos. Se busca determinar el número óptimo de integrantes del equipo y establecer un sistema eficiente de gestión de tickets para garantizar un flujo de trabajo eficiente.

Para llevar a cabo este análisis, se utilizarán datos históricos recopilados a través de la plataforma de gestión de proyectos Jira. Estos datos proporcionan información detallada sobre el intervalo entre arribos de los tickets, el tiempo de espera en el backlog, el tiempo de resolución de los tickets y el puntaje asociado a cada ticket, que estima su complejidad y tiempo de resolución.

El proyecto contará con 4 colas de prioridad distintas: Highest, High, Medium y Low. Cada ticket será clasificado en una de estas colas según su nivel de prioridad.

El objetivo principal es calcular el tiempo promedio de permanencia de un ticket en el sistema, lo que permitirá evaluar la eficacia del equipo en la gestión de tareas pendientes en cada una de las colas de prioridad. Además, se analizará el promedio de tickets resueltos por semana y el promedio de desfase de ticket, lo que proporcionará una visión clara del rendimiento del equipo en términos de productividad y capacidad de cumplimiento de plazos en cada una de las colas de prioridad.

**Evento actualización**: cuando ocurre este evento se calcula la prioridad de los tickets pendientes en el backlog en base al estimado de tiempo que van a tardar y a cuanto tiempo llevan en el backlog. Si un ticket se puede resolver en poco tiempo y lleva mucho tiempo en backlog, sube de prioridad. Luego del cálculo se mueven los tickets a la cola de prioridad que corresponda.

Este evento actualización posee una frecuencia que está determinada por la variable de control “**Tiempo de actualización de prioridades de tickets**”

Esperamos poder observar como una cantidad de desarrolladores determinada sube o baja la cantidad de tickets que se resuelven en un lapso de tiempo. Y a su vez, poder observar como influye la frecuencia en la que se actualizan las prioridades de los tickets en la cantidad de tickets resueltos
A través de este análisis detallado, la empresa podrá determinar el número ideal de integrantes del equipo y establecer procesos eficientes de gestión de tickets para maximizar la productividad y eficiencia en el desarrollo del software.

Exógenas de Datos:
  * **Intervalo entre arribos de tickets (IA)**: Se registrará el tiempo entre la llegada de cada ticket al backlog del proyecto. 
  * **Tiempo de resolución de un ticket (TR)**: Se anotará el tiempo necesario para resolver cada ticket del backlog del proyecto.
  * **Puntaje del ticket (PT)**: Estimación del tiempo que demorara en ser completado el ticket.

Exógenas de Control:
  * **Cantidad de desarrolladores**: Representará el número total de desarrolladores asignados al proyecto.
  * **Tiempo de actualización de prioridades de tickets**: cantidad de tiempo en la que ocurrirá un evento de actualización de tickets.
  * **Valor de cada punto de estimación de tickets**: Representará el valor de tiempo que cada punto de estimación tiene

Endógenas de Estado:
  * **Cantidad de tickets pendientes en prioridad HIGHEST (NS1)**: Representa la cantidad de tickets pendientes en el backlog con prioridad HIGHEST en un momento dado.
  * **Cantidad de tickets pendientes en prioridad HIGH (NS2)**: Representa la cantidad de tickets pendientes en el backlog con prioridad HIGH en un momento dado.
  * **Cantidad de tickets pendientes en prioridad MEDIUM (NS3)**: Representa la cantidad de tickets pendientes en el backlog con prioridad MEDIUM en un momento dado.
  * **Cantidad de tickets pendientes en prioridad LOW (NS4)**: Representa la cantidad de tickets pendientes en el backlog con prioridad LOW en un momento dado.

Endógenas de Resultado:
  * **Tiempo promedio de permanencia en el sistema de los tickets (PPS)**: Esto indicará cuánto tiempo en promedio permanece un ticket desde su creación hasta el momento en el que queda resuelto.
  * **Promedio de tickets resueltos por semana (PTS)**: Medirá la eficiencia del equipo en términos de la cantidad de trabajo completado en un período de una semana.
  * **Promedio de desfase de ticket (PDT)**: tiempo promedio que un ticket demora en ser realizado comparado con su tiempo estimado.

