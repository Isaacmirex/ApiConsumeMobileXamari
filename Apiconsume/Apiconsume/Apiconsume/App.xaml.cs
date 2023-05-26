using System;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace Apiconsume
{
    public partial class App : Application
    {
        public App()
        {
            InitializeComponent();

            MainPage = new NavigationPage( new Apiconsume.Paginas.Login.Page1()); //MainPage(); 
        }

        protected override void OnStart()
        {
        }

        protected override void OnSleep()
        {
        }

        protected override void OnResume()
        {
        }
    }
}
