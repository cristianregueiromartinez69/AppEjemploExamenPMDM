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

### 3. Composables :smile:
Vamos a ver ejemplos de estructuras de composables

```bash
@Composable
fun MyApp(viewModel: ViewModel) {
    var pulsado by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    val textoGame by viewModel.nombreLiveData.observeAsState(viewModel.getNombre())
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val backgroundImage = painterResource(id = R.drawable.fondoinicio) 
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column {
            if (!pulsado){
                Login({
                    pulsado = true
                      }, viewModel, remember { mutableStateOf(text) })
            }
            else{
                Game(viewModel, textoGame)
            }
        }
    }
}
```
Esta sería una Composable principal que va llamando a otras composables.
**Aspectos a observar** :open_mouth:
```bash
#val textoGame by viewModel.nombreLiveData.observeAsState(viewModel.getNombre())
Esto sirve para recoger datos del viewModel y poder repintarlos en las composables
```

```bash
#val backgroundImage = painterResource(id = R.drawable.fondoinicio) 
        #Image(
            #painter = backgroundImage,
            #contentDescription = null,
            #contentScale = ContentScale.Crop,
            #modifier = Modifier.fillMaxSize()
        #)

Todo esto es para dibujar una imagen
```

```bash
#Box(
        #modifier = Modifier
            #.fillMaxSize()
    #)
Esto es una caja que recibe un modifier para modificar su aspecto
```

### IMPORTANTE :scream:
Para poder moficiar el aspecto de las composables, debemos de usar el Objeto modifier y sus metodos, otro ejemplo de composable modificada con Modifier
```bash
@Composable
fun Login(funcion:() -> Unit, viewModel: ViewModel, texto: MutableState<String>) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 30.dp, start = 30.dp)
    ) {

        Row {
            TextoInicial(texto = "¡Adivina el numero!")
        }
        Row {
            NombreInicio()
        }
        Row {
            TextNombreEscribir(texto)
        }
        Row {
            Buttonenter(funcion, viewModel, texto)
        }

    }
}
```

```bash
#modifier = Modifier
            #.padding(top = 30.dp, start = 30.dp)
Podemos observar que dentro de los parámetros de colum, usamos el modifier que nos servirá para modificar el aspecto que tendrá todo lo que vaya en el Colum
```

# 4. (onclick) :smile:
En las composables, podemos usar el Button y este tiene una función onClick()

 ```bash
@Composable
fun Buttonenter(funcion: () -> Unit, viewModel: ViewModel, texto: MutableState<String>){

    viewModel.checkText(texto.value)

    var _activo by remember { mutableStateOf(viewModel.estadoLiveData.value!!.enterActivo) }

    viewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = viewModel.estadoLiveData.value!!.enterActivo
    }

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 20.dp, start = 30.dp)) {
        Button(
            enabled = _activo,
            onClick = {
                viewModel.setNombre(texto.value)
                funcion()
            },
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
            ),
            modifier = Modifier
                .padding(top = 80.dp)
                .size(width = 150.dp, height = 60.dp)
        ){
            Text(
                text = "Enter",
                color = Color.White,
                fontSize = 20.sp
            )
        }
    }
}
```

Si nos fijamos, veremos que en el onclick, podemos hacer lo que queramos, incluso yo le estoy pasando una funcion al propio onClick
```bash
#  onClick = {
                #viewModel.setNombre(texto.value)
                #funcion()
            #},
```
La función que le paso es para esto 
```bash
#   pulsado = true
                      #}, viewModel, remember { mutableStateOf(text) })
```
Lo que hacemos es pasarle una funcion que hace que cambie el valor de un booleano a true para cambiar de vista de UI y cambiar un texto que será el nombre que tenga que introducir el jugador


# 5. MVVN :smile:
El patrón MVVM (Model-View-ViewModel) es un patrón de arquitectura de software que se utiliza principalmente en aplicaciones de desarrollo de interfaces de usuario. Este patrón ayuda a separar la lógica de negocio y la lógica de presentación, facilitando un código más limpio, mantenible y escalable. MVVM es popular en frameworks de desarrollo como Android y frameworks de frontend web como Angular.

**Componentes de MVVM**
- Model (Modelo):

```bash
#Representa la capa de datos de la aplicación.
```
Contiene la lógica de negocio y las reglas para manejar los datos.
Puede ser un objeto de base de datos, un servicio web, un repositorio, etc.
No tiene conocimiento de la vista ni de la ViewModel.


- View (Vista):
```bash
#Es la interfaz de usuario, es decir, lo que el usuario ve e interactúa.
```
Debe ser lo más simple posible y solo encargarse de la presentación de los datos.
Se conecta con la ViewModel para obtener los datos y mostrar actualizaciones.
No contiene lógica de negocio.


- ViewModel:
```bash
#Es el puente entre la vista y el modelo.
```
Gestiona la lógica de presentación y mantiene la vista actualizada con los datos que provienen del modelo.
No tiene referencia directa a la vista, lo que permite que la vista observe a la ViewModel.
Contiene propiedades y métodos a los que la vista puede suscribirse para recibir notificaciones de cambios de estado.

## Ejemplo de codigo :bowtie:

```bash
#Model
object Datos {

    var nombre = ""
}

enum class Estados(val enterActivo:Boolean) {
    VACIO(enterActivo = false),
    COMPLETO(enterActivo = true)
}

```

```bash
#Ejemplo de vista
@Composable
fun Game(viewModel: ViewModel, text: String) {

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val backgroundImage = painterResource(id = R.drawable.fondoinicio)
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column {
            NombreJUgador(text)
        }
        Column(modifier = Modifier
            .padding(top = 130.dp)) {
            Row {
                CreateButton(Color.Red)
                CreateButton(Color.Yellow)

            }
            Row {
                CreateButton(Color.Cyan)
                CreateButton(Color.DarkGray)
            }
            Row {
                CreateButton(Color.Green)
                CreateButton(Color.Blue)
            }
            Row {
                StartButton(Color.Magenta)
            }
        }

    }
}

@Composable
fun NombreJUgador(text: String) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 30.dp, start = 90.dp)
    ) {

        Text(
            text = "Jugador: $text",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CreateButton(color: Color) {
    Button(
        onClick = {
        },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
        ),
        modifier = Modifier
            .padding(top = 60.dp, start = 20.dp)
            .size(width = 150.dp, height = 60.dp)
    ) {

    }
}

@Composable
fun StartButton(color: Color) {
    Button(
        onClick = {
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
        ),
        modifier = Modifier
            .padding(top = 60.dp, start = 90.dp)
            .size(width = 200.dp, height = 100.dp)
            .clip(CircleShape)
    ) {

    }
}

```

```bash
#Ejemplo de viewModel
class ViewModel:ViewModel(){

    val estadoLiveData : MutableLiveData<Estados> = MutableLiveData(Estados.VACIO)
    private val _nombreLiveData = MutableLiveData<String>()
    val nombreLiveData : LiveData<String> get() = _nombreLiveData

    init {
        _nombreLiveData.value = Datos.nombre
    }


    fun checkText(texto:String){
        if (texto.isEmpty()){
            getEstadoVacio()
        }
        else{
            getEstadoCompleto()
        }
    }

    fun setNombre(nombre:String){
        Datos.nombre = nombre
        _nombreLiveData.value = Datos.nombre
    }

    fun getNombre():String{
        return Datos.nombre
    }

    fun getEstadoVacio(){
        estadoLiveData.value = Estados.VACIO
    }

    fun getEstadoCompleto(){
        estadoLiveData.value = Estados.COMPLETO
    }
}
```
**Aspectos a tener en cuenta**
```bash
#La clase viewModel debe de heredar de ella, tal que así
class ViewModel:ViewModel()
```

# 6. Datos (enum, object, listas) :bowtie:

Dentro de nuestro MVVM, tenemos un apartado que son los Datos, que es ha donde el viewModel va a recoger la información para luego mostrarla a la vista.

```bash
#ejemplo clase Enum
enum class Estados(val enterActivo:Boolean) {
    VACIO(enterActivo = false),
    COMPLETO(enterActivo = true)
}

enum class EstadosJugando(val startActivo:Boolean, val buttonColorActivo:Boolean){
    INICIO(startActivo = true, buttonColorActivo = false),
    GENERANDO(startActivo = false, buttonColorActivo = false),
    ADIVINANDO(startActivo = false, buttonColorActivo = true)
}
```
En el ejemplo anterio tenemos unas clases enum con unos estados. Las clases enum son bastante buenas para observar estados, lo veremos más adelante

```bash
#ejemplo de clase Object
object Datos {

    var nombre = ""
    var random = 0
    var numeroJugador = 0
    var listaNumerosBotones : MutableList<Int> = mutableListOf(1,2,3,4,5,6)
    var aciertos = 0
}
```
ESto sería un singleton. Es Una instancia unica de un objeto, podemos llamarlo y cambiarle el valor a las variables

# 7. byRemenber, (MutableLiveData(Esto es opcional)) :bowtie:
En las composables, las variables no se redibujarán, a no ser que usemos esto
```bash
var _color by remember { mutableStateOf(color.colorInicio) }
```
Cuando usamos el by remenber, es como si le dijeramos al programa, que se acuerde del valor anterior para así poder redibujarlo

```bash
#Ejemplo con mutableLiveData
val aciertos by viewModel.aciertosLiveData.observeAsState(viewModel.getAciertos())
```
Imaginemos que en un juego de adivinar el numero, como el de esta aplicacion de mierda, queremos poner una composable en la cual hay un texto y un valor para los aciertos. 
Queremos que ese valor se actualice según vayamos ganando las rondas. Para ello, usaremos lo anterior que hará de observador de esto 

```bash
private var _aciertosLiveData = MutableLiveData<Int>()
val aciertosLiveData: LiveData<Int> get() = _aciertosLiveData
```
Tenemos un MutableLiveData que será de tipo entero
```bash
fun setAciertos(){
        Datos.aciertos += 1
        _aciertosLiveData.value = Datos.aciertos
    }

    fun perdedorAciertos(){
        Datos.aciertos = 0
        _aciertosLiveData.value = Datos.aciertos
    }
```
Podemos añadirle un valor y luego llamarlo desde la UI y este se redibujará

# 8. Observable :bowtie:
Una manera de observar aalgo lo hicimos en el punto anterior, pero ese no lo explicó damian, vamos con el que explicó este buen hombre.
```bash
val estadoLiveData : MutableLiveData<Estados> = MutableLiveData(Estados.VACIO)
val estadoJuegoLiveData : MutableLiveData<EstadosJugando> = MutableLiveData(EstadosJugando.INICIO)
```
Recordais la clase estados que teníamos antes, pues con esto que hemos escrito, hemos declarado 2 MutableLiveData, pero de tipo Estados y lo hemos iniciado en INICIO.
Puedes iniciarlo a lo que te salga de los huevos.

**IMPORTANTE**:scream:
Luego, tenemos que llamarlo en los metodos del viewModel en los cuales queremos que se cambien los estados, tal que así
```bash
estadoJuegoLiveData.value = EstadosJugando.GENERANDO
```

Luego en la UI, hacemos lo siguiente

```bash
@Composable
fun CreateButton(viewModel: ViewModel, numeroColor:Int, color: Color, textButton:String, colorLetra:Color) {

    var _activo by remember { mutableStateOf(viewModel.estadoJuegoLiveData.value!!.buttonColorActivo) }

    viewModel.estadoJuegoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = viewModel.estadoJuegoLiveData.value!!.buttonColorActivo
    }

    Button(
        enabled = _activo,
        onClick = {
            viewModel.addNumber(numeroColor, viewModel.getRandom())
        },
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
        ),
        modifier = Modifier
            .padding(top = 60.dp, start = 20.dp)
            .size(width = 150.dp, height = 60.dp)
    ) {
       Text(
           text = textButton,
           fontSize = 20.sp,
           fontWeight = FontWeight.ExtraBold,
           color = colorLetra
       )
    }
}
```

Las Lineas que nos importan son estas:
```bash
var _activo by remember { mutableStateOf(viewModel.estadoJuegoLiveData.value!!.buttonColorActivo) }

    viewModel.estadoJuegoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = viewModel.estadoJuegoLiveData.value!!.buttonColorActivo
    }

    Button(
        enabled = _activo,
```
Declaramos una variable que será la observada:
```bash
var _activo by remember { mutableStateOf(viewModel.estadoJuegoLiveData.value!!.buttonColorActivo) }
```

Declaramos el observer y dentro la variable a observar:
```bash
viewModel.estadoJuegoLiveData.observe(LocalLifecycleOwner.current) {
        _activo = viewModel.estadoJuegoLiveData.value!!.buttonColorActivo
    }
```
Esta variable cambiará según cambie lo que tenemos asignado para ser observado, en este caso los botones de colores. Luego esa variable la podemos asignar a si el boton está o no disponible con la funcoon enabled:
```bash
Button(
        enabled = _activo,
#Resto de codigo...
```
Con esto haremos que el enables esté o no activo dependendiendo del estado que está siendo observado

# 9. Curutinas
Podemos definir las curutinas como si fueran hilos, que no lo son en verdad, pero podría entenderse así. Nos sirve para que en nuestras aplicaciones pasen cosas en segundo plano sin que afecte a la aplicacion principal y su funcionamiento. Ejemplo de curutina.

```bash
#Curutina en la UI
    var _activo by remember { mutableStateOf(viewModel.estadoJuegoLiveData.value!!.startActivo) }
     LaunchedEffect(_color){
            while(_activo){
                delay(1000)
                _color = color.colorParpadeo
                delay(1000)
                _color = color.colorInicio
                delay(1000)
            }
        }
```
Esto sería una curutina en la UI.

**COMO FUNCIONA**:astonished:
Tenemos un bloque de codigo que empieza por LaunchedEffect y por parametro le vamos a pasar un color, podriamos pasarle cualquier cosa con la cual queramos que ocurra algo.
Dentro del metodo tenemos un bucle while que indica que mientras la variable activo este presente, es decir, en nuestro caso, sea true, esté visible para el usuario, ya que es lo que hace esa variable, los colores del boton de start van a ir cambiando con un delay de 1 segundo

```bash
#Curutina en el ViewModel
private fun cambiosColores(lista_Random: MutableList<Int>){
        viewModelScope.launch {
            for(i in 0 until lista_Random.size){
                if(lista_Random[i] == 1){
                    delay(500)
                    _colorRojoLiveData.value = Color(0xFFFF9999)
                    delay(500)
                    _colorRojoLiveData.value = ColoresIluminados.ROJO_PARPADEO.colorNomal
                    delay(500)
                }
                else if(lista_Random[i] == 2){
                    delay(500)
                    _colorVerdeLiveData.value = Color(0xFFA8FFAA)
                    delay(500)
                    _colorVerdeLiveData.value = ColoresIluminados.VERDE_PARPADEO.colorNomal
                    delay(500)
                }
                else if(lista_Random[i] == 3){
                    delay(500)
                    _colorAzulLiveData.value = Color(0xFF5F85FF)
                    delay(500)
                    _colorAzulLiveData.value = ColoresIluminados.AZUL_PARPADEO.colorNomal
                    delay(500)
                }
                else if (lista_Random[i] == 4){
                    delay(500)
                    _colorAmarilloLiveData.value = Color(0xFFFCFFBE)
                    delay(500)
                    _colorAmarilloLiveData.value = ColoresIluminados.AMARILLO_PARPADEO.colorNomal
                    delay(500)
                }
            }
        }
    }
```
Esta curutina es del juego de simon dice y la uso para cambiar los colores de los botones. Aqui empieza con viewModelScope.launch, el resto es practicamente igual, podemos establecer delays como en los hilos y luego este metodo lo podemos llamar en donde queramos y se ejecutará segun lo programado
