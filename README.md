# convert_app_android_to_ndk

El presente código de una aplicación simple y sencilla de android se usará para transformar el código java a código realmente nativo de android hecho con
c usando el ndk, siguiendo las instrucciones de 1 video que tengo disponible en mi canal de youtube, el cual es un video oculto y que solo puede ser visto
teniendo la dirección del video, se comienza a realizar la transformación del código.

Esto con la finalidad de aumentar la seguridad de que el código de la aplicación sea más dificil de obtener, ya que al ser compilado a nivel máquina y ser
almacenado en una libreria dinámica (mejor conocidos como dll en windows y so en linux, android y en mac, así como los dylib en ios), luego se vincula el
código de esta libreria con el código java mediante el JNI, el cual es una herramienta del NDK, en el video explico todo el proceso de transformación de
código.

También al transformar el código de android a codigo de c/c++ se aumenta el rendimiento de la aplicación, y también se reduce considerablemente el tamaño
de la aplicación, además de poder manipular en tiempo de compilación otros datos que aumentan el rendimiento de la aplicación android.

También explico otras caracteristicas que se pueden conseguir al usar el NDK para crear aplicación de android, o si estas ya estan creadas en cualquier
momento se puede agregar el uso del NDK y aumentar la seguridad, rendimiento y tener acceso a caracteristicas que normalmente no se pueden usar en android
aún usando java o kotlin, ya que estas caracteristicas solo estan disponibles al usar el NDK
