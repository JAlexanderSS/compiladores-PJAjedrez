package org.compiladores.blancas;

public class piezas {
    public static class Peon {
        int x;
        int y = 2;
        boolean jugado = false;
        boolean estado = true;

        public Peon(int x) {
            this.x = x;
        }

        public void mover(int newX, int newY) {
            this.x = newX;
            this.y = newY;
        }

        public void marcarJugado() {
            this.jugado = true;
        }

        public void cambiarEstado(boolean nuevoEstado) {
            this.estado = nuevoEstado;
        }
    }

    public static void main(String[] args) {
        Peon[] peones = new Peon[] {
                new Peon(1),
                new Peon(2),
                new Peon(3),
                new Peon(4),
                new Peon(5),
                new Peon(6),
                new Peon(7),
                new Peon(8)
        };

        // Accediendo a los datos de los peones
        for (Peon peon : peones) {
            System.out.println("X: " + peon.x + ", Y: " + peon.y + ", Jugado: " + peon.jugado + ", Estado: " + peon.estado);
        }

        // Modificando la información de un peón
        peones[0].mover(3, 4);
        peones[1].marcarJugado();
        peones[2].cambiarEstado(false);

        // Mostrando la información actualizada de los peones
        System.out.println("\nDespués de la modificación:");
        for (Peon peon : peones) {
            System.out.println("X: " + peon.x + ", Y: " + peon.y + ", Jugado: " + peon.jugado + ", Estado: " + peon.estado);
        }
    }
}
