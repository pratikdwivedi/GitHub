function usernameCheck()
    {
    if(document.form1.username.value=="") 
	alert ("Pleaes enter username"); 
    document.form1.username.focus(); 
     return false;
    }