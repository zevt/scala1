package cookbook
//class Recipe(
//              val ingredients: List[String] = List.empty,
//              val directions: List[String] = List.empty
//            ) {
//
//}
//
//object Recipe {
//  def apply(ingredients: List[String], directions: List[String]): Recipe
//  = new Recipe(ingredients, directions)
//
//  def unapply(recipe: Recipe): Option[(List[String], List[String] )] =
//    Some(recipe.ingredients, recipe.directions)
//}

case class Recipe(private val ingredients: List[String] = List.empty,
                  private val directions: List[String] = List.empty)