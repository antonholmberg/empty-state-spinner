# Empty State Spinner

So usually when I see people talk about spinners I see people hating on them. I actually quite enjoy
the built in spinner. The only feature I was missing was an initial empty state for the spinner.

So this is my attempt at not re-inventing the spinner but rather extending it with an initial
(optional) empty state.

## Get it

```groovy

implementation 'org.grunkspin:empty-state-spinner:0.1'

```

The package is available on jcenter so you have to include that as a repository.

```groovy
allprojects {
    repositories {
        jcenter()
    }
}
```

## Usage

This is an example of how to use the spinner:

```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var spinner: EmptyStateSpinner
    private val options = arrayListOf("Option one", "Option two", "Option three")
    

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinner = findViewById(R.id.spinner)
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options)
        spinner.setAdapter(adapter, "Empty")
    }
}
```

As you can se the only addition to using a regular spinner is that the *setAdapter* method now has
and additional empty state. The empty state can be anything as long as it matches the type of the
*ArrayAdapter*.

**IMPORTANT** The underlying list in the array adapter has to be a list that supports removing and
inserting elements. So if you use something like *listOf* instead of *arrayListOf* the *setAdapter*
method will throw an *UnsupportedOperationException*.

## Contributing

Feel free to contribute and submit pull requests. There are currently only Instrumentation tests for
the spinner.

If you don't want to contribute at all but just use it please feel free to do so.

## License


    Copyright 2017 Anton Holmberg
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
