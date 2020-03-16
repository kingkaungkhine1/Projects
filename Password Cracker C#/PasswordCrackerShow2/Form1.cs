using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace PasswordCrackerShow2
{
    public partial class frmPwdCracker1 : Form
    {
        string Wheel = "0123456789AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz <SP>!\"#$%&'()*+,-./:;<=>?@[]^_`{|}~";

        public frmPwdCracker1()
        {
            InitializeComponent();
        }

        private void btnCrackIt_Click(object sender, EventArgs e)
        {
            crack(UserPassword.Text);
        }

        string ReplaceChar(string inputstring, int posTarget, char ch)
        {
            char[] CharArray = inputstring.ToCharArray();
            CharArray[posTarget] = ch;
            return new string(CharArray);
        }

        char getNextChar(char ch)
        {
            if (ch == '\"')
                return ' ';
            for (int i = 0; i < Wheel.Length; i++)
                if (ch == Wheel[i])
                    return Wheel[i + 1];

            return ch;
        }

        public void crack(string userPwd)
        {
            string guessedPwd;
            int userPwdLength = 0;
            userPwdLength = userPwd.Length;
            guessedPwd = "aaaaaaaa";
            guessedPwd = guessedPwd.Substring(0, userPwdLength);

            double dMax = Math.Pow(Wheel.Length, userPwdLength);
            int maxStep = (dMax > 100000 ? 100000 : (int)dMax);
            ulong eachStep = (ulong)(dMax / maxStep);

            probar.Minimum = 0;
            probar.Maximum = maxStep;
            probar.Step = 1;
            probar.Value = 0;
            probar.Refresh();
            ulong counter = 0;

            DateTime startTime = DateTime.Now;

            while (1 == 1)
            {
                for (int i = 0; i < Wheel.Length; i++)
                {
                    guessedPwd = ReplaceChar(guessedPwd, userPwdLength - 1, Wheel[i]);
                    if (userPwd == guessedPwd)
                    {
                        lblCracking.Text = string.Format("Your password is:");
                        txtBoxGuessPwd.Text = guessedPwd;
                        probar.Value = probar.Maximum;
                        return;
                    }
                    txtBoxGuessPwd.Text = guessedPwd;
                    txtBoxGuessPwd.Refresh();

                    counter++;
                    if (counter % eachStep == 0)
                        probar.PerformStep();

                    TimeSpan tSpan = DateTime.Now - startTime;
                    tmrLbl.Text = tSpan.ToString();
                    tmrLbl.Refresh();
                }

                guessedPwd = ReplaceChar(guessedPwd, userPwdLength - 1, ' ');

                for (int j = userPwdLength - 2; j >= 0; j--)
                {
                    if (guessedPwd[j + 1] == ' ')
                    {
                        guessedPwd = ReplaceChar(guessedPwd, j + 1, 'a'); 
                        char nextChar = getNextChar(guessedPwd[j]);
                        guessedPwd = ReplaceChar(guessedPwd, j, nextChar); 

                    }
                }
            }
        }
    }
}