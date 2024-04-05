import random

def generar_movimiento_aleatorio():
    letras = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h']
    numeros = ['1', '2', '3', '4', '5', '6', '7', '8']
    
    from_casilla = random.choice(letras) + random.choice(numeros)
    to_casilla = random.choice(letras) + random.choice(numeros)
    
    return from_casilla, to_casilla

if __name__ == "__main__":
    num_movimientos = 50000
    
    with open("movimientos_aleatorios.txt", "w") as file:
        for _ in range(num_movimientos):
            movimiento = generar_movimiento_aleatorio()
            file.write(f"{movimiento[0]}-{movimiento[1]}\n")

