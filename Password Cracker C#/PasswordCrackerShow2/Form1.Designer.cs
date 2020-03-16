namespace PasswordCrackerShow2
{
    partial class frmPwdCracker1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(frmPwdCracker1));
            this.label1 = new System.Windows.Forms.Label();
            this.UserPassword = new System.Windows.Forms.TextBox();
            this.btnCrackIt = new System.Windows.Forms.Button();
            this.fixTmrlbl = new System.Windows.Forms.Label();
            this.tmrLbl = new System.Windows.Forms.Label();
            this.txtBoxGuessPwd = new System.Windows.Forms.TextBox();
            this.lblCracking = new System.Windows.Forms.Label();
            this.probar = new System.Windows.Forms.ProgressBar();
            this.SuspendLayout();
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(229, 116);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(388, 25);
            this.label1.TabIndex = 0;
            this.label1.Text = "Enter a password in eight characters or less";
            // 
            // UserPassword
            // 
            this.UserPassword.Location = new System.Drawing.Point(234, 174);
            this.UserPassword.MaxLength = 8;
            this.UserPassword.Name = "UserPassword";
            this.UserPassword.PasswordChar = '*';
            this.UserPassword.Size = new System.Drawing.Size(383, 20);
            this.UserPassword.TabIndex = 1;
            // 
            // btnCrackIt
            // 
            this.btnCrackIt.Location = new System.Drawing.Point(387, 200);
            this.btnCrackIt.Name = "btnCrackIt";
            this.btnCrackIt.Size = new System.Drawing.Size(75, 23);
            this.btnCrackIt.TabIndex = 2;
            this.btnCrackIt.Text = "Crack It!";
            this.btnCrackIt.UseVisualStyleBackColor = true;
            this.btnCrackIt.Click += new System.EventHandler(this.btnCrackIt_Click);
            // 
            // fixTmrlbl
            // 
            this.fixTmrlbl.AutoSize = true;
            this.fixTmrlbl.Location = new System.Drawing.Point(231, 261);
            this.fixTmrlbl.Name = "fixTmrlbl";
            this.fixTmrlbl.Size = new System.Drawing.Size(74, 13);
            this.fixTmrlbl.TabIndex = 3;
            this.fixTmrlbl.Text = "Time Elapsed:";
            // 
            // tmrLbl
            // 
            this.tmrLbl.AutoSize = true;
            this.tmrLbl.Location = new System.Drawing.Point(312, 261);
            this.tmrLbl.Name = "tmrLbl";
            this.tmrLbl.Size = new System.Drawing.Size(0, 13);
            this.tmrLbl.TabIndex = 4;
            // 
            // txtBoxGuessPwd
            // 
            this.txtBoxGuessPwd.Location = new System.Drawing.Point(234, 329);
            this.txtBoxGuessPwd.Name = "txtBoxGuessPwd";
            this.txtBoxGuessPwd.Size = new System.Drawing.Size(383, 20);
            this.txtBoxGuessPwd.TabIndex = 7;
            // 
            // lblCracking
            // 
            this.lblCracking.AutoSize = true;
            this.lblCracking.Location = new System.Drawing.Point(366, 313);
            this.lblCracking.Name = "lblCracking";
            this.lblCracking.Size = new System.Drawing.Size(128, 13);
            this.lblCracking.TabIndex = 8;
            this.lblCracking.Text = "Guessing Your Password:";
            // 
            // probar
            // 
            this.probar.Location = new System.Drawing.Point(234, 373);
            this.probar.Name = "probar";
            this.probar.Size = new System.Drawing.Size(383, 23);
            this.probar.TabIndex = 9;
            // 
            // frmPwdCracker1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackgroundImage = ((System.Drawing.Image)(resources.GetObject("$this.BackgroundImage")));
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.ClientSize = new System.Drawing.Size(867, 435);
            this.Controls.Add(this.probar);
            this.Controls.Add(this.lblCracking);
            this.Controls.Add(this.txtBoxGuessPwd);
            this.Controls.Add(this.tmrLbl);
            this.Controls.Add(this.fixTmrlbl);
            this.Controls.Add(this.btnCrackIt);
            this.Controls.Add(this.UserPassword);
            this.Controls.Add(this.label1);
            this.Name = "frmPwdCracker1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox UserPassword;
        private System.Windows.Forms.Button btnCrackIt;
        private System.Windows.Forms.Label fixTmrlbl;
        private System.Windows.Forms.Label tmrLbl;
        private System.Windows.Forms.TextBox txtBoxGuessPwd;
        private System.Windows.Forms.Label lblCracking;
        private System.Windows.Forms.ProgressBar probar;
    }
}

