from flask import Flask 
from flask import render_template, request, redirect, url_for, session                  
from flaskext.mysql import MySQL
from flask import send_from_directory                                 
import os

app = Flask(__name__)   
app.secret_key = 'your secret key'  


mysql = MySQL()
app.config['MYSQL_DATABASE_HOST']='localhost' 
app.config['MYSQL_DATABASE_USER']='root' 
app.config['MYSQL_DATABASE_PASSWORD']='' 
app.config['MYSQL_DATABASE_DB']='gamemate' 
mysql.init_app(app) 

carpeta = os.path.join('uploads')
app.config['CARPETA']= carpeta                                                                  




@app.route('/')
def home():
    return render_template('html/index.html')




######################## Formulario/Crear #############################################
@app.route('/formulario')
def registrar():
    return render_template('html/formulario.html')

@app.route('/store', methods=['POST'])                                                      
def store():
    nickname=request.form["user"]
    nombre=request.form["Nombre"]
    apellido=request.form["surname"]
    fechaNacimiento=request.form["bday"]
    provincia=request.form["prov"]
    horario=request.form["Horario"]
    email=request.form["email"]
    password=request.form["password"]
    foto=request.files["textFoto"]
    descripcion=request.form["descripcion"]    
    
    foto.save("uploads/" + foto.filename)                                                 
    sql = "INSERT INTO usuarios VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s);"
    datos=(nickname,nombre,apellido,fechaNacimiento,provincia,horario,email,password,foto.filename,descripcion)
    
    conn= mysql.connect()
    cursor= conn.cursor()
    cursor.execute(sql, datos)                                                              
    conn.commit()
    return redirect('/')

######################## Login #############################################
@app.route('/login', methods =['GET', 'POST'])
def login():
    msg = ''
    if request.method == 'POST' and 'username' in request.form and 'password' in request.form:
        username = request.form['username']
        password = request.form['password']
        conn = mysql.connect()
        cursor = conn.cursor()
        cursor.execute('SELECT * FROM usuarios WHERE nickname = %s AND contraseña = %s', (username, password))
        probando = cursor.fetchall()

        
        if probando:
            session['loggedin'] = True
            session['username'] = username
            session['password'] = password #Tengo problema con esto ya que me dice q acepta tuplas o integer
            msg = 'Logged in successfully !'
            return render_template('html/usuario.html', msg = msg)
        else:
            msg = 'Incorrect username / password !'
    return render_template('html/login.html', msg = msg)

@app.route('/logout')
def logout():
    session.pop('loggedin', None)
    session.pop('id', None)
    session.pop('username', None)
    return redirect(url_for('login'))









if __name__ == '__main__':                                                                      
    app.run(debug=True)





