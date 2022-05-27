package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class Member extends Controller{
    public static void index() {
        Logger.info("Rendering member");
        render ("member.html");
    }
}
