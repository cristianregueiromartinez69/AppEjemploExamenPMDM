## ESTO VA A SER UN README DE EXPLICACIÓN SEGÚN VAYA HACIENDO ESTA MIERDA DE APP

**Objetivos**:bowtie:
- Integrar todo lo que puede preguntar en el examen
- Poner explicaciones de cada cosa
- Hacer de esto una guía de apuntes


**Indice**:blush:

- Ciclos de vida de las aplicaciones
- Log cat
- Composables
- Modificacion y diseño de Composables, (onclick)
- MVVM
- Datos (enum, object, listas)
- byRemenber, (MutableLiveData(Esto es opcional))
- Observable
- Curutinas


### 1. Ciclos de vida de las aplicaciones :smile:
Las aplicaciones tienen 7 ciclos de vida por los que pasa nuestra actividad, son los siguientes:

- OnCreate -> Indica que la actividad se ha creado
- OnStart -> Indica que la actividad está a punto de ser visible para el usuario
- OnResume -> Indica que la actividad es visible para el usuario e interactúa con ella
- OnPause -> Indica que la actividad está a punto de ser pausada
- OnStop -> Indica que la actividad ya no es visible para el usuario
- OnRestart -> Indica que la actividad está a punto de ser reiniciada
- OnDestroy -> Indica que la actividad está a punto de ser destruida

### 2. Log cat :smile:
El Log cat es una herramienta muy util para mirar errores o comprobar cosas. Podemos mirarlo como si fuera
un System.out.println de java. Ejemplo:
```bash
#ejemplo de log cat dentro del OnCreate
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            
                Log.d("OnCreate", "Me encuentro dentro del onCreate")
            
        }
    }
}
```

Aquí ponemos un log cat dentro del OnCreate para asegurarnos de que la actividad ha empezado.
Para comprobarlo nos iríamos al log cat, Introduciríamos el Tag (OnCreate) y deberíamos de ver el mensage


 








