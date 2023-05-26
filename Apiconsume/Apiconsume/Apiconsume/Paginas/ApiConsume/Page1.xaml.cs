using Apiconsume.Models;
using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace Apiconsume.Paginas.Crud
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class Page1 : ContentPage
    {
        public Page1()
        {
            InitializeComponent();
        }
        private string URL = "https://apiinterna.azurewebsites.net/api";

        private void Btn_Leer(object sender, EventArgs e)
        {
            using (var wc = new WebClient())
            {
                if (string.IsNullOrEmpty(txtCab_id.Text))
                {
                    txtCab_id.Text = "CAB-0001";
                }

                wc.Headers.Add("Content-Type", "application/json");

                try
                {
                    var json = wc.DownloadString(URL + "/AjusteCabeceras/" + txtCab_id.Text);
                    var cabecera_detalle = Newtonsoft.Json.JsonConvert.DeserializeObject<Models.cabeceraDetalle>(json);

                    if (cabecera_detalle != null)
                    {
                        txtCab_id.Text = cabecera_detalle.cab_id;
                        txtCab_doc.Text = cabecera_detalle.cab_doc;
                        txtCab_descripcion.Text = cabecera_detalle.cab_descripcion;
                        txtCab_fecha.Date = cabecera_detalle.cab_fecha;
                    }
                    else
                    {
                        txtCab_id.Text = "";
                        txtCab_doc.Text = "";
                        txtCab_descripcion.Text = "";
                        txtCab_fecha.Date = DateTime.Now;
                    }
                }
                catch (WebException ex)
                {
                    var response = ex.Response as HttpWebResponse;
                    if (response != null && response.StatusCode == HttpStatusCode.NotFound)
                    {
                        // Manejar el caso de que no se encuentre el recurso (404 - Not Found)
                        Console.WriteLine("ID no encontrado.");
                        txtCab_id.Text = "";
                        txtCab_doc.Text = "";
                        txtCab_descripcion.Text = "";
                        txtCab_fecha.Date = DateTime.Now;
                    }
                    else
                    {
                        // Manejar otros errores de la solicitud
                        Console.WriteLine("Error de la solicitud: " + ex.Message);
                    }
                }
            }
        }

        private void Btn_Insertar(object sender, EventArgs e)
        {
            using (var wc = new WebClient())
            {
                wc.Headers.Add("Content-Type", "application/json");

                // Crear un objeto cabeceraDetalle con los datos del formulario
                var nuevoCabecera = new cabeceraDetalle
                {
                    cab_id = txtCab_id.Text,
                    cab_doc = txtCab_doc.Text,
                    cab_descripcion = txtCab_descripcion.Text,
                    cab_fecha = txtCab_fecha.Date
                };

                // Serializar el objeto cabeceraDetalle a formato JSON
                var json = JsonConvert.SerializeObject(nuevoCabecera);

                
                    // Enviar la solicitud POST a la API para insertar el nuevo objeto cabeceraDetalle
                    var respuesta = wc.UploadString(URL + "/AjusteCabeceras", "POST", json);

                    if (!string.IsNullOrEmpty(respuesta))
                    {
                        // Manejar la respuesta de la API después de insertar el objeto cabeceraDetalle
                        // (puede ser necesario realizar alguna acción adicional)
                        Console.WriteLine("Insertado correctamente.");
                    }
                    else
                    {
                        Console.WriteLine("Error al insertar.");
                    }
               
            }
        }

        private void Btn_Actualizar(object sender, EventArgs e)
        {
            using (var wc = new WebClient())
            {
                wc.Headers.Add("Content-Type", "application/json");

                // Crear un objeto cabeceraDetalle con los datos del formulario
                var nuevoCabecera = new cabeceraDetalle
                {
                    cab_id = txtCab_id.Text,
                    cab_doc = txtCab_doc.Text,
                    cab_descripcion = txtCab_descripcion.Text,
                    cab_fecha = txtCab_fecha.Date
                };

                // Serializar el objeto cabeceraDetalle a formato JSON
                var json = JsonConvert.SerializeObject(nuevoCabecera);

                
                    // Enviar la solicitud PUT a la API para actualizar el objeto cabeceraDetalle
                    var respuesta = wc.UploadString(URL + "/AjusteCabeceras/" + txtCab_id.Text, "PUT", json);

                    if (!string.IsNullOrEmpty(respuesta))
                    {
                        // Manejar la respuesta de la API después de actualizar el objeto cabeceraDetalle
                        // (puede ser necesario realizar alguna acción adicional)
                        Console.WriteLine("Actualizado correctamente.");
                    }
                    else
                    {
                        Console.WriteLine("Error al actualizar.");
                    }
                
               
            }
        }

        private void Btn_Eliminar(object sender, EventArgs e)
        {
            using (var wc = new WebClient())
            {
                // Obtener el ID del producto a eliminar
                string cab_id = txtCab_id.Text;

                try
                {
                    // Enviar la solicitud DELETE a la API para eliminar el producto
                    wc.UploadString(URL + "/AjusteCabeceras/" + cab_id, "DELETE", "");
                    txtCab_id.Text = "";
                    txtCab_doc.Text = "";
                    txtCab_descripcion.Text = "";
                    txtCab_fecha.Date = DateTime.Now;

                    Console.WriteLine("eliminado correctamente.");
                }
                catch (Exception ex)
                {
                    Console.WriteLine("Error al eliminar : " + ex.Message);
                }
            }

        }
    }
}