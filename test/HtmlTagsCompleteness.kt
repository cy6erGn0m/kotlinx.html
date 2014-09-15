package kotlinx.html.test

import org.junit.*
import kotlin.test.*
import kotlinx.html.*

class TestHtml5Completeness() {
    Test fun RenderAllTags() {
        val page = html {
            GenerateAllPossibleTags()
        }.toString()
        assertEquals(
                """<!DOCTYPE html>
<html>
  <head>
    <meta name="name" content="content"></meta>
    <title></title>
    <base href="href" target="target"></base>
    <link rel="stylesheet" type="text/css" href="href"/>
    <script type="text/javascript"></script>
  </head>
  <body>
    <a></a>
    <abbr></abbr>
    <address></address>
    <article></article>
    <aside></aside>
    <audio>
      <source></source>
      <track></track>
    </audio>
    <b></b>
    <blockquote></blockquote>
    <br/>
    <button></button>
    <canvas></canvas>
    <cite></cite>
    <code></code>
    <del></del>
    <details>
      <summary></summary>
    </details>
    <dfn></dfn>
    <div></div>
    <dl><dd></dd><dt></dt></dl>
    <em></em>
    <embed></embed>
    <fieldset>
      <legend></legend>
    </fieldset>
    <figure>
      <figcaption></figcaption>
    </figure>
    <footer></footer>
    <form></form>
    <h1></h1>
    <h2></h2>
    <h3></h3>
    <h4></h4>
    <h5></h5>
    <h6></h6>
    <header></header>
    <hr/>
    <i></i>
    <img/>
    <input></input>
    <ins></ins>
    <kbd></kbd>
    <label></label>
    <mark></mark>
    <meter></meter>
    <nav></nav>
    <noscript></noscript>
    <ol>
      <li></li>
    </ol>
    <p></p>
    <pre></pre>
    <progress></progress>
    <q></q>
    <s></s>
    <samp></samp>
    <section></section>
    <select>
      <optgroup>
        <option></option>
      </optgroup>
      <option></option>
    </select>
    <small></small>
    <span></span>
    <strong></strong>
    <sub></sub>
    <sup></sup>
    <table>
      <caption></caption>
      <tr>
        <th></th>
        <td></td>
      </tr>
      <colgroup>
        <col></col>
      </colgroup>
      <thead></thead>
      <tbody></tbody>
      <tfoot></tfoot>
    </table>
    <textarea></textarea>
    <time></time>
    <u></u>
    <ul>
      <li></li>
    </ul>
    <var></var>
    <video>
      <source></source>
      <track></track>
    </video>
    <wbr/>
    <script type="text/javascript"></script>
  </body>
</html>"""
                , page)
    }
}

fun HTML.GenerateAllPossibleTags() {
    head {
        // Defines information about the document
        meta("name", "content") {
            // Defines metadata about an HTML document
        }
        title {
            // Defines a title for the document
        }
        base("href", "target") {
            // Specifies the base URL/target for all relative URLs in a document
        }
        link("href") {
            // Defines the relationship between a document and an external resource (most used to link to style sheets)
        }
        script {
            // Defines a client-side script
        }
    }
    body {
        // Defines the document's body
        a {
            // Defines a hyperlink
        }
        abbr {
            // Defines an abbreviation
        }
        address {
            // Defines contact information for the author/owner of a document
        }
        article {
            // Defines an article
        }
        aside {
            // Defines content aside from the page content
        }
        audio {
            // Defines sound content
            source {
                // Defines multiple media resources for media elements (<video> and <audio>)
            }
            // Defines a video or movie
            track {
                // Defines text tracks for media elements (<video> and <audio>)
            }

        }
        b {
            // Defines bold text
        }
        /*
                bdi {
                    // Low support in browsers
                    // Isolates a part of text that might be formatted in a different direction from other text outside it
                }
                bdo {
                    // Low need
                    // Overrides the current text direction
                }
        */
        blockquote {
            // Defines a section that is quoted from another source
        }
        br()
        button {

        }
        canvas {
            // Used to draw graphics, on the fly, via scripting (usually JavaScript)
        }
        cite {
            // Defines the title of a work
        }
        code {
            // Defines a piece of computer code
        }
        /*
                command {
                    // Low support in browsers
                    // Defines a command button that a user can invoke
                }
        */
        /*
                datalist {
                    // Low support in browsers
                    // Specifies a list of pre-defined options for input controls
                }
        */
        del {
            // Defines text that has been deleted from a document
        }
        details {
            // Defines additional details that the user can view or hide
            summary {
                // Defines a visible heading for a <details> element
            }
        }
        dfn {
            // Defines a definition term
        }
        /*
                dialog {
                    // Low support in browsers
                    // Defines a dialog box or window
                }
        */
        div {
            // Defines a section in a document
        }
        dl {
            dd {
                // Defines a description of an item in a definition list
            }

            // Defines a definition list
            dt {
                // Defines a term (an item) in a definition list
            }
        }
        em {
            // Defines emphasized text
        }
        embed {
            // Defines a container for an external (non-HTML) application
        }
        fieldset {
            // Groups related elements in a form
            legend {
                // Defines a caption for a <fieldset>, <figure>, or <details> element
            }
        }
        figure {
            // Specifies self-contained content
            figcaption {
                // Defines a caption for a <figure> element
            }
        }
        footer {
            // Defines a footer for a document or section
        }
        form {
            // Defines an HTML form for user input
        }
        h1 {
            // to <h6>	Defines HTML headings
        }
        h2 {
            // to <h6>	Defines HTML headings
        }
        h3 {
            // to <h6>	Defines HTML headings
        }
        h4 {
            // to <h6>	Defines HTML headings
        }
        h5 {
            // to <h6>	Defines HTML headings
        }
        h6 {
            // to <h6>	Defines HTML headings
        }
        header {
            // Defines a header for a document or section
        }
        hr()
        i {
            // Defines a part of text in an alternate voice or mood
        }
        /*
                iframe {
                    // Discouraged!
                    // Defines an inline frame
                }
        */
        img {
            // Defines an image
        }
        input {
            // Defines an input control
        }
        ins {
            // Defines a text that has been inserted into a document
        }
        kbd {
            // Defines keyboard input
        }
        /*
                keygen {
                    // Low need
                    // Defines a key-pair generator field (for forms)
                }
        */
        label {
            // Defines a label for an <input> element
        }
        /*
                map {
                    // Low need
                    // Defines a client-side image-map
                    area {
                        // Defines an area inside an image-map
                    }
                }
        */
        mark {
            // Defines marked/highlighted text
        }
        /*
                menu {
                    // Low support by browsers
                    // Defines a list/menu of commands
                }
        */
        meter {
            // Defines a scalar measurement within a known range (a gauge)
        }
        nav {
            // Defines navigation links
        }
        noscript {
            // Defines an alternate content for users that do not support client-side scripts
        }
        ol {
            // Defines an ordered list
            li {
                // Defines a list item
            }

        }
        /*
                output {
                    // Low support by browsers & low need
                    // Defines the result of a calculation
                }
        */
        p {
            // Defines a paragraph
        }
        pre {
            // Defines pre-formatted/space-preserving text
        }
        progress {
            // Represents the progress of a task
        }
        q {
            // Defines a short quotation
        }
        /*
                // Eastern Asian advanced support postponed
                rp {
                    // Defines what to show in browsers that do not support ruby annotations
                }
                rt {
                    // Defines an explanation/pronunciation of characters (for East Asian typography)
                }
                ruby {
                    // Defines a ruby annotation (for East Asian typography)
                }
        */
        s {
            // Defines text that is no longer correct
        }
        samp {
            // Defines sample output from a computer program
        }
        section {
            // Defines a section in a document
        }
        select {
            // Defines a drop-down list
            optgroup {
                // Defines a group of related options in a drop-down list
                option {
                    // Defines an option in a drop-down list
                }
            }
            option {
                // Defines an option in a drop-down list
            }

        }
        small {
            // Defines smaller text
        }
        span {
            // Defines a section in a document
        }
        strong {
            // Defines important text
        }
        sub {
            // Defines subscripted text
        }
        sup {
            // Defines superscripted text
        }
        table {
            caption {
                // Defines a table caption
            }
            // Defines a table
            tr {
                th {
                    // Defines a header cell in a table
                }

                // Defines a row in a table
                td {
                    // Defines a cell in a table
                }

            }
            colgroup {
                // Specifies a group of one or more columns in a table for formatting
                col {
                    // Specifies column properties for each column within a <colgroup> element
                }
            }

            thead {
                // Groups the header content in a table
            }

            tbody {
                // Groups the body content in a table
            }
            tfoot {
                // Groups the footer content in a table
            }

        }
        textarea {
            // Defines a multi-line input control (text area)
        }
        time {
            // Defines a date/time
        }
        u {
            // Defines text that should be stylistically different from normal text
        }
        ul {
            // Defines an unordered list
            li {
                // Defines a list item
            }

        }
        variable {
            //  <var> Defines a variable
        }
        video {
            source {
                // Defines multiple media resources for media elements (<video> and <audio>)
            }
            // Defines a video or movie
            track {
                // Defines text tracks for media elements (<video> and <audio>)
            }

        }
        wbr()
        script {
            // Defines a client-side script
        }
    }
}
