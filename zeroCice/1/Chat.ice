module Demo
{
    interface Chat
    {
        void sendMessage(string s);
		
		string getLastMessage();
    }
}