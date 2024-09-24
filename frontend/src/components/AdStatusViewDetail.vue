<template>

    <v-card outlined>
        <v-card-title>
            AdStatus # {{$route.params.id }}
        </v-card-title>

        <v-card-text>
            <div>
                <Number label="MaterialId" v-model="item.materialId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="MaterialName" v-model="item.materialName" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="MaterialUrl" v-model="item.materialUrl" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="AdId" v-model="item.adId" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="AdStatus" v-model="item.adStatus" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Title" v-model="item.title" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Content" v-model="item.content" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="Budget" v-model="item.budget" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="Target" v-model="item.target" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Number label="TargetImpressions" v-model="item.targetImpressions" :editMode="editMode" @change="change" />
            </div>
            <div>
                <String label="DeliveryStatus" v-model="item.deliveryStatus" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="StartDate" v-model="item.startDate" :editMode="editMode" @change="change" />
            </div>
            <div>
                <Date label="EndDate" v-model="item.endDate" :editMode="editMode" @change="change" />
            </div>
        </v-card-text>
    </v-card>

</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'AdStatusViewDetail',
    props: {
      value: Object,
    },
    data: () => ({
        item : [],
    }),
    async created() {
      var params = this.$route.params;
      var temp = await axios.get(axios.fixUrl('/adStatuses/' + params.id))

      this.item = temp.data;

    },
    methods: {
    }
  }
</script>

