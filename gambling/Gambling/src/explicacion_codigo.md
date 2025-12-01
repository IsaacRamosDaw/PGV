# Explicación del Código: Simulación de Casino Multihilo

Este documento detalla el funcionamiento del proyecto de simulación de apuestas, el cual utiliza programación concurrente en Java para modelar la interacción entre una banca (casino), un croupier y múltiples jugadores con diferentes estrategias.

## 1. Resumen General

El sistema simula una mesa de ruleta donde varios jugadores realizan apuestas simultáneamente mientras un croupier gestiona los turnos. La aplicación utiliza **hilos (Threads)** para representar a cada actor (Croupier y Jugadores), permitiendo que actúen de forma independiente pero coordinada a través del tiempo y recursos compartidos.

## 2. Arquitectura de Clases

### 2.1. Recursos Compartidos y Control

*   **`Banca.java`**:
    *   Representa el "recurso compartido" principal.
    *   Mantiene el saldo del casino (inicialmente 50.000) y el número ganador actual.
    *   **Sincronización**: Métodos críticos como `perderDinero`, `bancaGano` y `cambiarNumero` están marcados como `synchronized` para evitar condiciones de carrera (race conditions) cuando múltiples hilos intentan modificar el saldo al mismo tiempo.
    *   Controla si la banca ha quebrado (`bancaBroke`).

*   **`Croupier.java`** (Runnable):
    *   Actúa como el reloj del juego.
    *   En su bucle principal (`run`), genera un nuevo número ganador cada 3 segundos (`Thread.sleep(3000)`).
    *   El ciclo continúa mientras la banca tenga dinero.

*   **`Lanzador.java`**:
    *   Es la clase principal (`main`).
    *   Inicializa la `Banca`.
    *   Crea e inicia el hilo del `Croupier`.
    *   Crea e inicia múltiples hilos de jugadores (4 de cada tipo: Concreto, Par/Impar, Martingala).

### 2.2. Jugadores (Herencia y Polimorfismo)

*   **`Gambler.java`**:
    *   Clase padre para todos los jugadores.
    *   Mantiene el estado común: `saldo` (inicial 1000), `nombre`, y referencia a la `Banca`.

*   **`JugadoNumeroConcreto.java`**:
    *   **Estrategia**: Elige un número fijo al azar (1-36) al nacer y siempre apuesta a ese número.
    *   **Apuesta**: Fija de 10 unidades.
    *   **Ganancia**: Si acierta, gana 360 (36x). Si falla, pierde 10.

*   **`JugadorParImpar.java`**:
    *   **Estrategia**: Elige apostar siempre a PAR o siempre a IMPAR.
    *   **Apuesta**: Fija de 10 unidades.
    *   **Ganancia**: Si acierta la paridad, gana 20 (2x).

*   **`JugadorMartinGala.java`**:
    *   **Estrategia**: La famosa estrategia "Martingala". Elige un número fijo.
    *   **Apuesta**: Comienza con 10. Si pierde, **dobla** su apuesta anterior para intentar recuperar pérdidas. Si gana, reinicia la apuesta a 10.
    *   **Riesgo**: Es el jugador que más rápido puede quedarse sin dinero o hacer quebrar a la banca debido al crecimiento exponencial de las apuestas.

## 3. Flujo de Ejecución y Concurrencia

El sistema funciona mediante un ciclo de tiempo coordinado implícitamente por `Thread.sleep`:

1.  **Inicio**: El `Lanzador` arranca todos los hilos.
2.  **Ciclo del Croupier**:
    *   Genera un número.
    *   Duerme 3000ms (3 segundos).
3.  **Ciclo de los Jugadores**:
    *   Entran en un bucle `while`.
    *   **Esperan**: Duermen 3000ms. La intención es sincronizarse con el giro de la ruleta del Croupier.
    *   **Apuestan**: Descuentan dinero de su saldo.
    *   **Comprueban**: Verifican si el número generado por la banca coincide con su apuesta.
    *   **Resolución**:
        *   Si ganan: Llaman a `casino.perderDinero()` (la banca paga).
        *   Si pierden: El dinero ya fue descontado o se notifica a la banca (`casino.bancaGano()`).
    *   **Pausa**: Pequeña pausa de 100ms antes de la siguiente ronda.

### Nota sobre Sincronización
El código utiliza una sincronización basada en tiempo (`sleep(3000)`). Esto simula que todos esperan a que la bola gire. Sin embargo, en sistemas reales, esto podría causar desincronización si un hilo se retrasa; una implementación más robusta usaría `wait()` y `notify()` para asegurar que los jugadores solo verifiquen el resultado *después* de que el Croupier haya "cantado" el número.

## 4. Condiciones de Fin

El juego termina para un hilo individual cuando:
*   El jugador se queda sin dinero (`imBroke`).
*   La banca se queda sin dinero (`casino.bancaBroke`).

Si la banca quiebra, el Croupier detiene su ciclo y el casino cierra.
