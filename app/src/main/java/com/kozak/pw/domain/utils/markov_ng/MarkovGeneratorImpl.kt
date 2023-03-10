package com.kozak.pw.domain.utils.markov_ng

import com.ppolivka.namegenerator.MarkovGenerator

/**
 * A procedural word generator that uses Markov chains built from a user-provided array of words.
 *
 * This uses Katz's back-off model, which is an approach that uses high-order models. It looks for the next letter based on the last "n" letters, backing down to lower order models when higher models fail.
 *
 * This also uses a Dirichlet prior, which acts as an additive smoothing factor, introducing a chance for random letters to be be picked.
 *
 * @param   data    Training data for the generator, an array of words.
 * @param   order   Highest order of model to use - models 1 to order will be generated.
 * @param   prior   The dirichlet prior/additive smoothing "randomness" factor.
 *
 * @see "http://www.samcodes.co.uk/project/markov-namegen/"
 * @see "https://en.wikipedia.org/wiki/Katz%27s_back-off_model"
 * @see "https://en.wikipedia.org/wiki/Additive_smoothing"
 */
class MarkovGeneratorImpl(data: Set<String>, order: Int = 2, prior: Float = 0.001f) :
    MarkovGenerator {

    /**
     * The set of Markov models used by this generator, starting from highest order to lowest order.
     */
    private var models: MutableSet<MarkovModel> = LinkedHashSet()

    /**
     * The highest order model used by this generator.
     *
     * Generators own models of order 1 through order "n".
     * Generators of order "n" look back up to "n" characters when choosing the next character.
     */
    private var order: Int

    /**
     * Dirichlet prior, acts as an additive smoothing factor.
     *
     * The prior adds a constant probability that a random letter is picked from the alphabet when generating a new letter.
     */
    private var prior: Float

    init {
        this.order = order
        this.prior = prior
        val letters: MutableSet<String> = LinkedHashSet()
        data.forEach {
            it.forEach { char ->
                letters.add(char.toString())
            }
        }
        val domain: MutableList<String> = ArrayList()
        domain.add("#")
        domain.addAll(letters.sorted())
        for (i in 0..order) {
            models.add(MarkovModel(data.map { it }, order - i, prior, domain))
        }
    }

    override fun generate(lengthRange: IntRange): String {
        var word: String = "#".repeat(order)
        var letter = letter(word)
        while (letter != "#") {
            if (letter != null) {
                word += letter
            }
            letter = letter(word)
        }
        val result = word.substring(order)

        return if (result.length in lengthRange) result
        else generate(lengthRange)
    }

    /**
     * Generates the next letter in a word.
     * @param   context The context the model will use for generating the next letter.
     * @return  The generated letter, or null if no model could generate one.
     */
    private fun letter(context: String): String? {
        var letterContext: String = context.substring(context.length - order, context.length)
        var letter: String? = null
        for (model in models) {
            letter = model.generate(letterContext)
            if (letter == null) {
                letterContext = letterContext.substring(1)
            } else {
                break
            }
        }
        return letter
    }
}
